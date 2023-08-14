package game;

import game.Item.Remembrance;
import game.Item.StarlightSyrup;
import game.Runes.GoldenRunes;
import game.Trade.FingerReaderEnia;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.EnemyCharacter.*;
import game.Environments.*;
import game.Player.Player;
import game.Trade.MerchantKale;
import game.Utils.FancyMessage;
import game.Utils.MapRepository;
import game.Utils.StartScenario;
import game.Reset.ResetManager;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),new Graveyard(),
				new GustOfWind(),new PuddleOfWater(),new Cliff(), new Cage(), new Barrack(), new LakeOfRot());

		List<String> map = MapRepository.Limgrave;
		GameMap limgrave = new GameMap(groundFactory, map);
		world.addGameMap(limgrave);

		List<String> map2 = MapRepository.RoundtableHold;
		GameMap roundtableHold = new GameMap(groundFactory, map2);
		world.addGameMap(roundtableHold);

		List<String> map3 = MapRepository.StormveilCastle;
		GameMap stormveilCastle = new GameMap(groundFactory, map3);
		world.addGameMap(stormveilCastle);

		List<String> map4 = MapRepository.BossRoom;
		GameMap bossRoom = new GameMap(groundFactory, map4);
		world.addGameMap(bossRoom);

		List<String> map5 = MapRepository.LakeOfRot;
		GameMap lakeOfRot = new GameMap(groundFactory, map5);
		world.addGameMap(lakeOfRot);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		limgrave.at(23, 17).addActor(new LoneWolf());


		StartScenario startScenario = new StartScenario();
		// HINT: what does it mean to prefer composition to inheritance?
		Player player = startScenario.consoleSelection();
		//Player player = new Player("Tarnished",'@',500,new Scimitar());
		world.addPlayer(player, limgrave.at(32, 10));
//		Enemies lw = new SkeletalBandit(new Scimitar());
//		gameMap.at(33, 10).addActor(lw);
//		Enemies gd = new GiantDog();
//		gameMap.at(36, 10).addActor(gd);
//		Enemies hv = new HeavySkeletalSwordsman(new Grossmesser());
//		gameMap.at(34, 11).addActor(hv);
//		Enemies gc = new GiantCrayfish();
//		gameMap.at(33, 12).addActor(gc);
//		limgrave.add('n',new NumberRange(0,10),new NumberRange(0,5));
//
//		limgrave.add('&',new NumberRange(65,10),new NumberRange(0,5));
//		limgrave.add('&',new NumberRange(10,10),new NumberRange(7,5)); // left gust of wind
//		limgrave.add('~',new NumberRange(0,5),new NumberRange(15,2));
//		limgrave.add('~',new NumberRange(40,2),new NumberRange(15,5)); // right puddle of water
		limgrave.at(38,11).setGround(new LostGrace());

		// Limgrave to and back from Rountable Hold
		Location LimgravetoRoundtableHold = limgrave.at(6,23);
		Location RoundtableholdtoLimgrave = roundtableHold.at(9,10);
		LimgravetoRoundtableHold.setGround(new GoldenFogDoor(RoundtableholdtoLimgrave,"Rountable Hold"));
		RoundtableholdtoLimgrave.setGround(new GoldenFogDoor(LimgravetoRoundtableHold,"Limgrave"));

		// Limgrave to and back from Stormveil Castle
		Location LimgravetoStormveilCastle = limgrave.at(29,0);
		Location StormveilCastletoLimgrave = stormveilCastle.at(38,23);
		LimgravetoStormveilCastle.setGround(new GoldenFogDoor(StormveilCastletoLimgrave,"Stormveil Castle"));
		StormveilCastletoLimgrave.setGround(new GoldenFogDoor(LimgravetoStormveilCastle,"Limgrave"));

		// BossRoom to and back from StormveilCastle
		Location StormveilCastletoBossRoom = stormveilCastle.at(20,0);
		Location BossRoom = bossRoom.at(0,4);
		StormveilCastletoBossRoom.setGround(new GoldenFogDoor(BossRoom,"Boss Room"));

		Location LimgravetoLakeOfRot = limgrave.at(60,9);
		Location LakeOfRottoLimgrave = lakeOfRot.at(11,13);
		LimgravetoLakeOfRot.setGround(new GoldenFogDoor(LakeOfRottoLimgrave,"Lake of Rot"));
		LakeOfRottoLimgrave.setGround(new GoldenFogDoor(LimgravetoLakeOfRot,"Limgrave"));

		Location LakeofRottoBossRoom = lakeOfRot.at(11,0);
		LakeofRottoBossRoom.setGround(new GoldenFogDoor(BossRoom,"Boss Room"));


//		Door gameMapDoor1 = new Door(toLimgrave, "Roundtable Hold");
//		toRoundtableHold.addItem(gameMapDoor1); // PROBLEM TO ADD THE DOOR TO THE MAP
////
//		Door gameMap2Door1 = new Door(toRoundtableHold, "Limgrave");
//		toLimgrave.addItem(gameMap2Door1);


		player.setLostGrace(limgrave.at(38,11));
		FingerReaderEnia traderE = new FingerReaderEnia();
		MerchantKale traderK = new MerchantKale();
		limgrave.addActor(traderE,limgrave.at(39,12));
		limgrave.addActor(traderK, limgrave.at(36, 10));

		ResetManager resetManager = ResetManager.getInstance();

		limgrave.at(68,12).addItem(new Remembrance());

		StarlightSyrup starlightSyrup = new StarlightSyrup();
		lakeOfRot.at(12,13).addItem(starlightSyrup);


		limgrave.at(17,10).addItem(new GoldenRunes());
		limgrave.at(20,15).addItem(new GoldenRunes());
		limgrave.at(14,18).addItem(new GoldenRunes());
		limgrave.at(19,20).addItem(new GoldenRunes());
		limgrave.at(45,12).addItem(new GoldenRunes());
		limgrave.at(37,18).addItem(new GoldenRunes());
		limgrave.at(55,10).addItem(new GoldenRunes());
		limgrave.at(60,15).addItem(new GoldenRunes());
		limgrave.at(64,18).addItem(new GoldenRunes());
		limgrave.at(53,20).addItem(new GoldenRunes());
		limgrave.at(10,7).addItem(new GoldenRunes());
		limgrave.at(47,9).addItem(new GoldenRunes());
		limgrave.at(53,3).addItem(new GoldenRunes());
		limgrave.at(40,7).addItem(new GoldenRunes());
		limgrave.at(16,5).addItem(new GoldenRunes());
		Location summongroundLocation = limgrave.at(31,20);
		summongroundLocation.setGround(new SummonGround(summongroundLocation));
		Location summongroundLocation2 = limgrave.at(56,17);
		summongroundLocation2.setGround(new SummonGround(summongroundLocation2));
		Location summongroundLocation3 = limgrave.at(27,8);
		summongroundLocation.setGround(new SummonGround(summongroundLocation3));
		Location summongroundLocation4 = bossRoom.at(2,2);
		summongroundLocation3.setGround(new SummonGround(summongroundLocation4));

		//try invader
//		Actor invader = new Invader(414,new GreatKnife());
//		Actor invader2 = new Invader(414,new Uchigatana());
//		Actor ally = new Ally(414,new GreatKnife());
//		Actor ally2 = new Ally(414,new Uchigatana());
//		gameMap.at(74,0).addActor(invader);
//		gameMap.at(18,7).addActor(invader2);
//		gameMap.at(30,4).addActor(ally);
//		gameMap.at(41,15).addActor(ally2);

		world.run();
	}
}
