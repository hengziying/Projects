from abc import ABC, abstractmethod
from typing import TypeVar, Generic

# from referential_array import ArrayR, T
T = TypeVar('T')

##############################################
# Fast Backup:                               #
# An algorithm to determine the maximum      #
# possible data throughput from the data     #
# center origin to the fdtarget data centers.#
##############################################
class Queue(ABC, Generic[T]):
    """ Abstract class for a generic Queue.
    *Reference from FIT1008 Queue ADT implementation
    """

    def __init__(self) -> None:
        self.length = 0

    @abstractmethod
    def append(self, item: T) -> None:
        """ Adds an element to the rear of the queue.
        *Reference from FIT1008 Queue ADT implementation
        """
        pass

    @abstractmethod
    def serve(self) -> T:
        """ Deletes and returns the element at the queue's front.
        *Reference from FIT1008 Queue ADT implementation
        """
        pass

    def __len__(self) -> int:
        """ Returns the number of elements in the queue.
        *Reference from FIT1008 Queue ADT implementation
        """
        return self.length

    def is_empty(self) -> bool:
        """ True if the queue is empty.
        *Reference from FIT1008 Queue ADT implementation
        """
        return len(self) == 0

    @abstractmethod
    def is_full(self) -> bool:
        """ True if the stack is full and no element can be pushed.
        *Reference from FIT1008 Queue ADT implementation
        """
        pass

    def clear(self):
        """ Clears all elements from the queue.
        *Reference from FIT1008 Queue ADT implementation
        """
        self.length = 0


class CircularQueue(Queue[T]):
    """ Circular implementation of a queue with arrays.

    Attributes:
         length (int): number of elements in the stack (inherited)
         front (int): index of the element at the front of the queue
         rear (int): index of the first empty space at the oback of the queue
         array (ArrayR[T]): array storing the elements of the queue

    ArrayR cannot create empty arrays. So MIN_CAPCITY used to avoid this.

    *Reference from FIT1008 Queue ADT implementation
    """
    MIN_CAPACITY = 1

    def __init__(self, max_capacity: int) -> None:
        Queue.__init__(self)
        self.front = 0
        self.rear = 0
        self.array = [None] * (max(self.MIN_CAPACITY, max_capacity))

    def append(self, item: T) -> None:
        """ Adds an element to the rear of the queue.
        :pre: queue is not full
        :raises Exception: if the queueu is full

        *Reference from FIT1008 Queue ADT implementation
        """
        if self.is_full():
            raise Exception("Queue is full")

        self.array[self.rear] = item
        self.length += 1
        self.rear = (self.rear + 1) % len(self.array)

    def serve(self) -> T:
        """ Deletes and returns the element at the queue's front.
        :pre: queue is not empty
        :raises Exception: if the queue is empty

        *Reference from FIT1008 Queue ADT implementation
        """
        if self.is_empty():
            raise Exception("Queue is empty")

        self.length -= 1
        item = self.array[self.front]
        self.front = (self.front + 1) % len(self.array)
        return item

    def is_full(self) -> T:
        """ True if the queue is full and no element can be appended.
        *Reference from FIT1008 Queue ADT implementation
        """
        return len(self) == len(self.array)

    def clear(self) -> None:
        """ Clears all elements from the queue.
        *Reference from FIT1008 Queue ADT implementation
        """
        Queue.__init__(self)
        self.front = 0
        self.rear = 0


class ResidualNetwork:
    """
       Function description: This function initiates the ResidualNetwork object

       Input:
           connections: A list which contains a number of tuple indicating the the ID of the data centre from which
           the communication channel departs, the ID of the data centre to which the communication channel arrives
           and a positive integer representing the maximum throughput of that channel.
           maxIn :  a list of integers in which specifies the maximum amount of incoming data foe each data centre
           maxOut : a list of integers in which specifies the maximum amount of outgoing data foe each data centre

       Time complexity: O(D+C), where D is the number of data centres.
       Aux space complexity: O(D), where D is the number of data centres.

   """
    def __init__(self,connections,maxIn,maxOut,source,targets):
        self.vertices = [None]*(2*len(maxIn)+1)
        self.max_vertex_id = 2*len(maxIn) #this will be the last vertex id in the vertices list that allow us to ues it to create the id of super sink
        self.counter = len(maxIn) # this counter is used to create id for extra vertices
        self.connections = connections
        #create the vertex and store the the maximum ingoing and outgoing data respectively
        for i in range(len(maxIn)):
            vertex = Vertex(i)
            vertex.max_data = min(maxIn[i], maxOut[i])
            vertex.maxIn = maxIn[i]
            vertex.maxOut = maxOut[i]
            self.vertices[i] = vertex
        self.source = None
        self.destination = None
        self.temp_path = None
        self.to_residual_network(source,targets)

    def to_residual_network(self,source,targets):
        """
           Function description: This function create the residual network by following the connections, source and targets.

           Input:
               source : An integer represents the ID of the source
               targets : A list of integers represent the ID of the targets

          Time complexity: O(C), where C is the number of connections.
          Aux space complexity: O(1)

       """
        #add the super sink vertex that will link with the targets
        id = self.max_vertex_id
        self.vertices[id] = Vertex(id)
        self.destination = self.vertices[id]
        for target in targets:
            u = self.vertices[target]
            #the maximum data of for target should be set to the maximum incoming data because target can only input and it does not has ouput
            u.max_data = u.maxIn
            v = self.vertices[id]
            foward_edge = Edge(u, v, u.maxIn)
            backward_edge = Edge(v, u, 0)
            foward_edge.backward_edge = backward_edge
            backward_edge.backward_edge = foward_edge
            u.add_edge(foward_edge)
            v.add_edge(backward_edge)

        #add filter to the source, which means that only maximum outgoing data can be given in the start
        self.source = self.vertices[source]
        u = self.source
        #an extra vertex is added to act as an filter
        self.vertices[self.counter] = Vertex(self.counter)
        v = self.vertices[self.counter]
        u.extra_vertex = self.counter #store the index of its extra vertex
        self.counter += 1
        #create the foward edge and backward edge for the residual graph, initialize the flow of backward edge to 0
        foward_edge = Edge(u, v, u.maxOut)
        backward_edge = Edge(v, u, 0)
        backward_edge.backward_edge = foward_edge
        foward_edge.backward_edge = backward_edge
        u.add_edge(foward_edge)
        v.add_edge(backward_edge)

        #loop through the connections to build the residual graph with the communication channels
        #filter will also be added at the front of every vertex to ensure the flow does not exceed the maximum capacity of ingoing or outgoing data
        for connection in self.connections:
            id1 = connection[0]
            id2 = connection[1]
            #if the vertex is a source, then we will be using the the extra vertex(filter) to link it with other vertex
            if id1 == source:
                u = self.vertices[self.source.extra_vertex]
            else:
                u = self.vertices[id1]
            v = self.vertices[id2]
            #if the extra vertex(filter) already exist
            if v.extra_vertex is not None:
                extra_vertex = self.vertices[v.extra_vertex]
            #if the extra vertex(filter) does not exist, create it
            else:
                self.vertices[self.counter] = Vertex(self.counter)
                extra_vertex = self.vertices[self.counter]
                v.extra_vertex = self.counter
                self.counter += 1
                # create the foward edge and backward edge for the residual graph, initialize the flow of backward edge to 0
                foward_edge1 = Edge(extra_vertex, v, v.max_data)
                backward_edge1 = Edge(v, extra_vertex, 0)
                backward_edge1.backward_edge = foward_edge1
                foward_edge1.backward_edge = backward_edge1
                extra_vertex.add_edge(foward_edge1)
                v.add_edge(backward_edge1)

            # create the foward edge and backward edge for the residual graph, initialize the flow of backward edge to 0
            foward_edge2 = Edge(u, extra_vertex, connection[2])
            backward_edge2 = Edge(extra_vertex, u, 0)
            backward_edge2.backward_edge = foward_edge2
            foward_edge2.backward_edge = backward_edge2
            u.add_edge(foward_edge2)
            extra_vertex.add_edge(backward_edge2)


    def has_AugmentingPath(self, source,destination):
        """
           Function description: This function check whether this residual network has path to augment by using BFS to trverse through the trie.

           Input:
               source : A vertex object represents the source vertex
               destination : A vertex object represents the destination vertex

           Output:
                Boolean value indicating the presence or absence of an augmenting path.
                Return true when there is path from the source to the destination.
                Return false when there is no path from the source to the destination.

           Time complexity: O(D+C), where D is the number of data centres and  C is the number of communication channels.
           Aux space complexity: O(D+C), where D is the number of data centres  where D is the number of data centres
                                and C is the number of communication channels.
       """
        temp_path = []
        # initialize discovered to a queue, FIFO and append the source into to it
        discovered = CircularQueue(self.max_vertex_id)
        discovered.append((source,None))
        trace = [source] #trace is use to keep track of what vertex has been discovered or visited
        while len(discovered) > 0:
            #serve from the queue, which will return the first item in the queue
            u,edge = discovered.serve()
            u.visited = True        # set vertex u to visited
            temp_path.append(edge)
            # if the vertex is equal to the destination vertex means that I have reach the destination
            # stop the iteration, return True directly
            if u == destination:
                self.temp_path = temp_path  # set the augment path to a list of visited edges
                self.refresh_vertex(trace)  # refresh the vertex in the trace
                return True
            # perform searching through the edges of vertex u
            for edge in u.edges:
                # if the flow of edge are bigger than 0 menas that this path can travel
                if edge.flow > 0 :
                    v = edge.v
                    # append vertex v into the discovered queue if the vertex v and the edge has not been discovered and visited
                    if v.discovered == False and v.visited == False:
                        discovered.append((v,edge))
                        trace.append(v)
                        v.discovered = True  # set vertex v to discovered

        # this happened when there is no path from the source to the vertex
        # return False
        self.refresh_vertex(trace) # refresh the vertex in the trace
        self.temp_path = None # set the augment path to None since there is no path
        return False

    def get_AugmentingPath(self):
        """
           Function description: To get the augment path and the residual capacity.

           Output:
            path: List of edges representing the augmenting path.
            residual_capacity: Residual capacity of the augmenting path.

           Time complexity: O(C), where C is the number of communication channels.
           Aux space complexity: O(C), where C is the number of communication channels.
       """
        path = self.temp_path
        capacity = []
        prev_edge = None
        augment_path =[]
        # find the augment path of from source to vertex by using the edges that we found previously in function has_AugmentingPath
        # the approach is to track from the last edge
        # check whether the u vertex of the last edge and v vertex of the next edge in the path are the same
        # if it is same means that they are adjacent edge
        for i in range(len(path)-1,0,-1):
            if prev_edge is not None:
                if prev_edge.u == path[i].v:
                    prev_edge = path[i]
                    augment_path.append(path[i])
            else:
                prev_edge = path[i]
                augment_path.append(path[i])
        # loop through the edges in the augment path to find which edge has the minimum flow
        for edge in augment_path:
            if edge is not None:
                capacity.append(edge.flow)
        residual_capacity = min(capacity)
        return augment_path,residual_capacity

    def augmentFlow(self,path,residual_capacity):
        """
           Function description: This function augment the flow of the residual network

           Input:
            path: List of edges representing the augmenting path.
            residual_capacity: Residual capacity of the augmenting path.

           Time complexity: O(C), where C is the number of communication channels.
           Aux space complexity: O(1)
       """
        for edge in path:
            if edge is not None:
                edge.flow -= residual_capacity
                edge.backward_edge.flow += residual_capacity


    def refresh_vertex(self,trace):
        """
           Function description: Refreshes the visited and discovered attributes of the vertices to False.

           Input:
               trace: List of vertices visited during the search.

           Time complexity: O(C), where C is the number of communication channels.
           Aux space complexity: O(1).
       """
        for v in trace:
            v.discovered = False
            v.visited = False

    def ford_fulkerson(self):
        """
            Function description:This function find the maximum flow in the residual network by using the Ford-Fulkerson algorithm.

            Approach description:
            First, initialize the flow to zero. Then, use a while loop and the function has_augmentingPath which traverse the graph using bfs
            to check whether there are path exist. If path exist, find the residual capacity of the path and add it into the flow.Lastly,
            augment the path with the residual capacity and repeat the same steps until there is no path left from the source to the destination.

            Output:
                flow: Maximum flow in the residual network.

            Time complexity: O(D*C^2), where D is the number of data centres and  C is the number of communication channels.
            Aux space complexity: O(1).
        """
        flow = 0
        residual_network = self
        while residual_network.has_AugmentingPath(self.source,self.destination):
            path,residual_capacity = residual_network.get_AugmentingPath()
            flow += residual_capacity
            residual_network.augmentFlow(path,residual_capacity)
        return flow




class Vertex:
    def __init__(self, id):
        """
        Function description: This function initiates the Vertex object

        Input:
            id: An integer indicating the id of the vertex.

        Time complexity: O(1)
        Aux space complexity: O(1)

        """
        self.id = id
        self.edges = []
        self.discovered = False
        self.visited = False
        self.max_data = None
        self.extra_vertex = None
        self.maxIn = None
        self.maxOut = None


    def add_edge(self, edge):
        """
        Function description: This function add edge to the vertex's array which store all the edges.

        Input:
            edge: An Edge object that are to be added into the array.

        Time complexity: O(1)
        Aux space complexity: O(1)

        """
        self.edges.append(edge)


class Edge:
    def __init__(self, u, v, flow):
        """
        Function description: This function initiates the Edge object

        Input:
            u: A Vertex object.
            v: A Vertex object.
            flow: An integer indicating the flow of the edges.
            backward_edge: An edge object indicating the backward edge at the same side

        Time complexity: O(1)
        Aux space complexity: O(1)

        """
        self.u = u
        self.v = v
        self.flow = flow
        self.backward_edge = None


def maxThroughput(connections, maxIn, maxOut, origin, targets):
    """
        Function description:This function find the maximum throughput from the data centre origin to the data centres targets using
                             the ford fulkerson algorithm.

        Approach description:
        The Ford-Fulkerson approach is used by the maxThroughput function to determine the maximum flow in a network. The technique
        finds augmenting path iteratively in the residual network from a source vertex to a destination vertex and updates the flow
        along these paths. Until there is no path left from the source to the destination, the process is repeated.

        Inside the function ford_fulkerson it will first set the maximum flow to 0. Then, use BFS to check whether an augmenting path
        exist. Next,get the augmenting path and calculate the residual capacity in the augmentation path. Update the maximum flow by
        adding the residual capacity found and also update the flow in the augmenting path.

        Input:
            connections: A list which contains a number of tuple indicating the the ID of the data centre from which the communication
                        channel departs, the ID of the data centre to which the communication channel arrives and a positive integer
                        representing the maximum throughput of that channel.
            maxIn :  a list of integers in which specifies the maximum amount of incoming data foe each data centre.
            maxOut : a list of integers in which specifies the maximum amount of outgoing data foe each data centre.
            origin: An integer indicating the ID of which data centre is the source where there contains the data to be backed up.
            targets: A list of integer indicating the ID of which data centres is the targets where the data should back up to.


        Output:
            max_throughput : An integer represents the maximum possible data throughput from the data centre origin to the data centres
                            specified in targets

        The building of residual network cost O(D+C) time complexity
        The finding of the maximum throughput using ford fulkerson costs O(D*C^2) time complexity

        Time complexity: O(C^2), where C is the number of communication channels.
        Aux space complexity: O(1).
    """
    residual_network = ResidualNetwork(connections,maxIn,maxOut,origin,targets)
    return residual_network.ford_fulkerson()

if __name__ == '__main__':
    # Example
    connections = [(0, 1, 3000), (1, 2, 2000), (1, 3, 1000),
    (0, 3, 2000), (3, 4, 2000), (3, 2, 1000)]
    maxIn = [5000, 3000, 3000, 3000, 2000]
    maxOut = [5000, 3000, 3000, 2500, 1500]
    origin = 0
    targets = [4, 2]
    # Your function should return the maximum possible data throughput from the
    # data centre origin to the data centres specified in targets.
    print(maxThroughput(connections, maxIn, maxOut, origin, targets))
    #4500

