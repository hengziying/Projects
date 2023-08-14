from abc import ABC, abstractmethod
from typing import TypeVar, Generic

# from referential_array import ArrayR, T
T = TypeVar('T')




############################################
# CatsTrie
############################################


class Node:
    """
       Function description: This function initiates the Node object

       Input:
           size: The size of links (default size 27, a~z and one terminal $ at index 0)
           data: The payload data for each node (None by default)

       Time complexity: O(1)
       Aux space complexity: O(N), where N is the input size

    """
    def __init__(self,size = 27,data = None):
        # data payload (the frequency if each node)
        self.data = data
        self.links = [None]*size
        # A tuple that stores the best next frequency character and the character index and is use tp keep track of the best frequncy sentence in auto complete
        self.best_freq = None




class CatsTrie:
    """
    Function description: This function initiates the CatsTrie object

    Input:
        sentences: An array contains a list of strings that should be insert into CatsTrie

    Time complexity: O(NM), where N is the number of string in input list, where M is the number of characters in the longest sentence.
    Aux space complexity: O(NM), where N is the number of string in input list, where M is the number of characters in the longest sentence.

    """
    def __init__(self,sentences):
        self.root = Node()
        self.longest_length = 0
        #loop throught the sentences and insert it into the trie
        for sentences in sentences:
            self.insert(sentences,0)



    def insert(self,key,data=None):
        """
             Function description: This insert a key(sentence) into the CatTrie by calling the auxiliary function insert_aux

             Input:
                 key: The key that are to be inserted into the CatTrie
                 data: the payload data for the node (None by default)

            Time complexity: O(M), where M is the length of the key
            Aux space complexity: O(1)

        """
        current = self.root
        current.best_freq = self.insert_aux(current,key,0)


    def insert_aux(self,current, key,index,data=None):
        """
              Function description: An auxiliary function for inserting the key(sentence) into the CatTrie

              Approach description: Besides than adding the sentences into the CatTrie, this function also keep track of
              the next best frequency node and store it in every node. The way of keeping track is that in every node there will
              be a tuple which contains the best frequency of the next node and the index best next character.

              Input:
                  current: The current node
                  key: The key that are to be inserted into the CatTrie
                  index: To keep track the index of key for the current recursion
                  data: the payload data for the node (None by default)

             Return: the best frequency tuple

               Time complexity: O(M), where M is the length of the key
               Aux space complexity: O(1)

        """
        # base case: when reached the last character of the key
        if index == len(key):
            #if the path exist
            if current.links[0] is not None:
                prev = current
                current = current.links[0]
                #increment the frequency of this node
                current.data = current.data + 1

            # if path does not exist
            else:
                prev = current
                # create new node
                current.links[0] = Node()
                current = current.links[0]
                current.data = 1
                # use last index (leave node) of each sentence to find the length of the longest sentence in the trie
                if index > self.longest_length:
                    self.longest_length = index
            #if the best frequency of the previous node is None
            if prev.best_freq is None:
                #set it to the frequency of the terminal node and 0 (index of terminal)
                prev.best_freq = (current.data,0)
            #if its not None
            else:
                # compare it with the previous stored data
                # if the current frequency is higher than or equal to the previous best frequency of the previous node
                if current.data >= prev.best_freq[0]:
                    # set it to the frequency of the terminal node and 0 (index of terminal)
                    prev.best_freq = (current.data, 0)

            return prev.best_freq

        # case when not the last character of the key
        else:
            prev = current
            #find the character and its index(between 1 to 27)
            char = key[index]
            char_index = ord(char) - 97 + 1
            # if path exist
            if current.links[char_index] is not None:
                current = current.links[char_index]
                current.data = current.data + 1

            # if path does not exist
            else:
                # create new node
                current.links[char_index] = Node()
                current = current.links[char_index]
                current.data = 1

            #call the function recursively
            self.insert_aux(current,key,index+1)

            # if the best frequency of the previous node is None
            # set it to a tuple contains best frequency of the current node and index of the current character
            if prev.best_freq is None:
                prev.best_freq = (current.best_freq[0], char_index)
            #if is not None
            else:
                #check the best frequency of the previous node with the current node, find which one is larger
                if current.best_freq[0] > prev.best_freq[0] :
                    prev.best_freq = (current.best_freq[0], char_index)
                #check whether the frequency of the previous node with the current node is equal and find which character is smaller, to make sure the lexicographically
                elif current.best_freq[0] == prev.best_freq[0] and char_index < prev.best_freq[1] :
                    prev.best_freq = (current.best_freq[0], char_index)
            return prev.best_freq





    def autoComplete(self, prompt):
        """
           Function description: This function will auto complete the string that you input.

           Approach description:
           First, check whether the length of prompt exceeds the longest sentence in the trie, return None if this is true.
           Else, start at the root node and go over each character in the trie based on the input. Move on to the following
           node if the current character is present in the trie by adding it to the prefix string. If the current character
           does not appear in the trie means that there are no sentences with the specified prefix, return None. Next,
           call the autoComplete_aux method once the traversal is finished to discover the completed sentence that begins
           with the provided prefix most frequently. Lastly, the method called will return the completed sentence.

           Traverse the trie based on the character in the input takes O(m) time.
           The autoComplete_aux function call requires only O(1) time.


           Input:
               prompt :  This string represents the incomplete sentence that is to be completed by the trie.

           Output:
            If the trie contains sentence that start with the input,a most frequent sentence in the trie that starting with the input.
            If more than one sentences has the same highest frequency,it returns lexicographically smaller sentence
            Else it will return None.

            Searching the character of prompt in the trie cost O(X) time complexity.
            Calling the auxiliary function of auto complete cost O(Y) time complxity.

            Time complexity: O(X + Y) where X is the length of the prompt and Y is the length of the most frequent sentence that
                            begins with the prompt.
            Aux space complexity: O(1)
        """
        # begin from the root node
        current = self.root
        # if the length of prompt is longer than the longest sentence in the trie, return None
        if len(prompt) > self.longest_length:
            return None

        else:
            for char in prompt:
                index = ord(char) - 97 + 1
                #if the path exist
                if current.links[index] is not None:
                    current = current.links[index]
                #if the path does not exist
                else:
                    return None

        #return the auxiliary function of auto complete to find which the function will return a sentence
        return self.autoComplete_aux(prompt,current)


    def autoComplete_aux(self, retStr, current):
        """
           Function description: This function is a auxiliary function for autoComplete which it will find the sentence with
           the highest frequency recursively.

           Approach description:
            The base case for this recursive function is when the next best frequency character of the current node is 0 (means terminal).
            Otherwise, the function will concatenate the next best frequency character of the current node into the retStr and traverse
            to the next best frequency node from the current node by accessing using the index of the character that has the best frequency.


           Input:
               retStr :  Represents the current completed sentence
               current : Represents the currently being processed node

           Output:
            retStr: A string representing the completed sentence with the highest frequency.

           Time complexity: O(Y), where Y is the length of the most frequent sentence in sentences that begins with the prompt.
           Aux space complexity: O(1)
        """
        # base case: if the current node is the last character of the best frequency sentence
        # by checking whether the best frequency character is 0 (0 means terminal)
        if current.best_freq[1] == 0:
            return retStr
        # if the current node is not the last character
        else:
            #append the next best frequency character into the string that are to be returned
            retStr += chr(current.best_freq[1]+97-1)
            # call the function recursively with the next node
            current = current.links[current.best_freq[1]]
            return self.autoComplete_aux(retStr,current)



if __name__ == '__main__':
    # Example 1, similar to the introduction
    # but with some additional sentences
    sentences = ["abc", "abazacy", "dbcef", "xzz", "gdbc", "abazacy", "xyz",
    "abazacy", "dbcef", "xyz", "xxx", "xzz"]
    # Creating a CatsTrie object
    mycattrie = CatsTrie(sentences)
    
    # Example 1.1
    # A simple example
    prompt = "ab"
    print("Example1.1: "+ str(mycattrie.autoComplete(prompt)))
    #output abazacy

    # Example 1.2
    # Another simple example
    prompt = "a"
    print("Example1.2: "+ str(mycattrie.autoComplete(prompt)))
    #output abazacy

    # Example 1.3
    # What if the prompt is the same as an existing sentence?
    prompt = "dbcef"
    print("Example1.3: "+ str(mycattrie.autoComplete(prompt)))
    #output dbcef

    # Example 1.4
    # What if the length is longer?
    prompt = "dbcefz"
    print("Example1.4: "+ str(mycattrie.autoComplete(prompt)))
    #output None

    # Example 1.5
    # What if sentences doesn’t exist.
    prompt = "ba"
    print("Example1.5: "+ str(mycattrie.autoComplete(prompt)))
    #output None

    # Example 1.6
    # A scenario where the tiebreaker is used
    prompt = "x"
    print("Example1.6: "+ str(mycattrie.autoComplete(prompt)))
    #output xyz

    # Example 1.7
    # A scenario where the prompt is empty
    prompt = ""
    print("Example1.7: "+ str(mycattrie.autoComplete(prompt)))
    #output abazacy


    # Example 2, similar to the Example 1
    # but with some minor channges
    sentences = ["abc", "abczacy", "dbcef", "xzz", "gdbc", "abczacy", "xyz",
    "abczacy", "dbcef", "xyz", "xxx", "xzz"]
    # Creating a CatsTrie object
    mycattrie = CatsTrie(sentences)

    # Example 2.1
    # A scenario where the prompt is the same as an existing sentence
    # but it can be completed with higher frequency
    prompt = "abc"
    print("Example2.1: "+ str(mycattrie.autoComplete(prompt)))
    #output abczacy






