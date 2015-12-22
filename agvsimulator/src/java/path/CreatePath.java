package path;

import jason.environment.grid.Location;

import java.util.ArrayList;
import java.util.List;

import agv.HospitalModel;

public class CreatePath {
	private List<Vertex> nodes;
	private List<Edge> edges;

	public CreatePath() {
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < HospitalModel.GSize; i++) {
			for (int j = 0; j < HospitalModel.GSize; j++) {
				Vertex location = new Vertex(new Location(i,j), "Node_" + i
						+ "_" + j);
				nodes.add(location);
			}
		}
		// 1st line
		addLane("Edge_00_10", new Location(0, 0), new Location(1, 0), 1);
		addLane("Edge_10_20", new Location(1, 0), new Location(2, 0), 1);
		addLane("Edge_20_30", new Location(2, 0), new Location(3, 0), 1);
		addLane("Edge_30_40", new Location(3, 0), new Location(4, 0), 1);
		addLane("Edge_40_50", new Location(4, 0), new Location(5, 0), 1);
		addLane("Edge_50_60", new Location(5, 0), new Location(6, 0), 1);
		addLane("Edge_60_70", new Location(6, 0), new Location(7, 0), 1);
		addLane("Edge_70_80", new Location(7, 0), new Location(8, 0), 1);
		addLane("Edge_80_90", new Location(8, 0), new Location(9, 0), 1);
		addLane("Edge_90_100", new Location(9, 0), new Location(10, 0), 1);
		addLane("Edge_100_110", new Location(10, 0), new Location(11, 0), 1);
		addLane("Edge_110_120", new Location(11, 0), new Location(12, 0), 1);
		addLane("Edge_120_130", new Location(12, 0), new Location(13, 0), 1);
		addLane("Edge_130_140", new Location(13, 0), new Location(14, 0), 1);
		addLane("Edge_140_150", new Location(14, 0), new Location(15, 0), 1);
		addLane("Edge_150_160", new Location(15, 0), new Location(16, 0), 1);
		addLane("Edge_160_170", new Location(16, 0), new Location(17, 0), 1);
		addLane("Edge_170_180", new Location(17, 0), new Location(18, 0), 1);
		addLane("Edge_180_190", new Location(18, 0), new Location(19, 0), 1);

		// 2nd line
		addLane("Edge_30_31", new Location(3, 0), new Location(3, 1), 1);
		addLane("Edge_70_71", new Location(7, 0), new Location(7, 1), 1);
		addLane("Edge_110_111", new Location(11, 0), new Location(11, 1), 1);
		addLane("Edge_150_151", new Location(15, 0), new Location(15, 1), 1);

		// 3rd line
		addLane("Edge_31_32", new Location(3, 1), new Location(3, 2), 1);
		addLane("Edge_71_72", new Location(7, 1), new Location(7, 2), 1);
		addLane("Edge_111_112", new Location(11, 1), new Location(11, 2), 1);
		addLane("Edge_151_152", new Location(15, 1), new Location(15, 2), 1);

		// 4th line
		addLane("Edge_32_33", new Location(3, 2), new Location(3, 3), 1);
		addLane("Edge_72_73", new Location(7, 2), new Location(7, 3), 1);
		addLane("Edge_73_83", new Location(7, 3), new Location(8, 3), 1);
		addLane("Edge_83_93", new Location(8, 3), new Location(9, 3), 1);
		addLane("Edge_93_103", new Location(9, 3), new Location(10, 3), 1);
		addLane("Edge_103_113", new Location(10, 3), new Location(11, 3), 1);
		addLane("Edge_112_113", new Location(11, 2), new Location(11, 3), 1);
		addLane("Edge_152_153", new Location(15, 2), new Location(15, 3), 1);

		// 5th line
		addLane("Edge_33_34", new Location(3, 3), new Location(3, 4), 1);
		addLane("Edge_73_74", new Location(7, 3), new Location(7, 4), 1);
		addLane("Edge_113_114", new Location(11, 3), new Location(11, 4), 1);
		addLane("Edge_153_154", new Location(15, 3), new Location(15, 4), 1);

		// 6th line
		addLane("Edge_05_15", new Location(0, 5), new Location(1, 5), 1);
		addLane("Edge_15_25", new Location(1, 5), new Location(2, 5), 1);
		addLane("Edge_25_35", new Location(2, 5), new Location(3, 5), 1);
		addLane("Edge_35_45", new Location(3, 5), new Location(4, 5), 1);
		addLane("Edge_45_55", new Location(4, 5), new Location(5, 5), 1);
		addLane("Edge_55_65", new Location(5, 5), new Location(6, 5), 1);
		addLane("Edge_65_75", new Location(6, 5), new Location(7, 5), 1);
		addLane("Edge_74_75", new Location(7, 4), new Location(7, 5), 1);
		addLane("Edge_115_125", new Location(11, 5), new Location(12, 5), 1);
		addLane("Edge_125_135", new Location(12, 5), new Location(13, 5), 1);
		addLane("Edge_135_145", new Location(13, 5), new Location(14, 5), 1);
		addLane("Edge_145_155", new Location(14, 5), new Location(15, 5), 1);
		addLane("Edge_154_155", new Location(15, 4), new Location(15, 5), 1);

		// 7th line
		addLane("Edge_35_36", new Location(3, 5), new Location(3, 6), 1);
		addLane("Edge_75_76", new Location(7, 5), new Location(7, 6), 1);
		addLane("Edge_76_86", new Location(7, 6), new Location(8, 6), 1);
		addLane("Edge_86_96", new Location(8, 6), new Location(9, 6), 1);
		addLane("Edge_96_106", new Location(9, 6), new Location(10, 6), 1);
		addLane("Edge_106_116", new Location(10, 6), new Location(11, 6), 1);
		addLane("Edge_115_116", new Location(11, 5), new Location(11, 6), 1);
		addLane("Edge_155_156", new Location(15, 5), new Location(15, 6), 1);

		// 8th line
		addLane("Edge_36_37", new Location(3, 6), new Location(3, 7), 1);
		addLane("Edge_76_77", new Location(7, 6), new Location(7, 7), 1);
		addLane("Edge_116_117", new Location(11, 6), new Location(11, 7), 1);
		addLane("Edge_156_157", new Location(15, 6), new Location(15, 7), 1);
		addLane("Edge_157_167", new Location(15, 7), new Location(16, 7), 1);
		addLane("Edge_167_177", new Location(16, 7), new Location(17, 7), 1);
		addLane("Edge_177_187", new Location(17, 7), new Location(18, 7), 1);
		addLane("Edge_187_197", new Location(18, 7), new Location(19, 7), 1);
		
		// 9th line
		addLane("Edge_08_18", new Location(0, 8), new Location(1, 8), 1);
		addLane("Edge_18_28", new Location(1, 8), new Location(2, 8), 1);
		addLane("Edge_28_38", new Location(2, 8), new Location(3, 8), 1);
		addLane("Edge_38_48", new Location(3, 8), new Location(4, 8), 1);
		addLane("Edge_48_58", new Location(4, 8), new Location(5, 8), 1);
		addLane("Edge_58_68", new Location(5, 8), new Location(6, 8), 1);
		addLane("Edge_68_78", new Location(6, 8), new Location(7, 8), 1);
		addLane("Edge_77_78", new Location(7, 7), new Location(7, 8), 1);
		addLane("Edge_117_118", new Location(11, 7), new Location(11, 8), 1);
		addLane("Edge_118_128", new Location(11, 8), new Location(12, 8), 1);
		addLane("Edge_128_138", new Location(12, 8), new Location(13, 8), 1);
		addLane("Edge_138_148", new Location(13, 8), new Location(14, 8), 1);
		addLane("Edge_148_158", new Location(14, 8), new Location(15, 8), 1);
		addLane("Edge_157_158", new Location(15, 7), new Location(15, 8), 1);

		// 10th line
		addLane("Edge_38_39", new Location(3, 8), new Location(3, 9), 1);
		addLane("Edge_78_79", new Location(7, 8), new Location(7, 9), 1);
		addLane("Edge_118_119", new Location(11, 8), new Location(11, 9), 1);
		addLane("Edge_158_159", new Location(15, 8), new Location(15, 9), 1);
		
		// 11th line
		addLane("Edge_39_310", new Location(3, 9), new Location(3, 10), 1);
		addLane("Edge_79_710", new Location(7, 9), new Location(7, 10), 1);
		addLane("Edge_119_1110", new Location(11, 9), new Location(11, 10), 1);
		addLane("Edge_159_1510", new Location(15, 9), new Location(15, 10), 1);
		addLane("Edge_1510_1610", new Location(15, 10), new Location(16, 10), 1);
		addLane("Edge_1610_1710", new Location(16, 10), new Location(17, 10), 1);
		addLane("Edge_1710_1810", new Location(17, 10), new Location(18, 10), 1);
		addLane("Edge_1810_1910", new Location(18, 10), new Location(19, 10), 1);
		
		//12th line
		addLane("Edge_011_111", new Location(0, 11), new Location(1, 11), 1);
		addLane("Edge_111_211", new Location(1, 11), new Location(2, 11), 1);
		addLane("Edge_211_311", new Location(2, 11), new Location(3, 11), 1);
		addLane("Edge_310_311", new Location(3, 10), new Location(3, 11), 1);
		addLane("Edge_311_411", new Location(3, 11), new Location(4, 11), 1);
		addLane("Edge_411_511", new Location(4, 11), new Location(5, 11), 1);
		addLane("Edge_511_611", new Location(5, 11), new Location(6, 11), 1);
		addLane("Edge_611_711", new Location(6, 11), new Location(7, 11), 1);
		addLane("Edge_710_711", new Location(7, 10), new Location(7, 11), 1);
		addLane("Edge_711_811", new Location(7, 11), new Location(8, 11), 1);
		addLane("Edge_811_911", new Location(8, 11), new Location(9, 11), 1);
		addLane("Edge_911_1011", new Location(9, 11), new Location(10, 11), 1);
		addLane("Edge_1011_1111", new Location(10, 11), new Location(11, 11), 1);
		addLane("Edge_1110_1111", new Location(11, 10), new Location(11, 11), 1);
		addLane("Edge_1111_1211", new Location(11, 11), new Location(12, 11), 1);
		addLane("Edge_1211_1311", new Location(12, 11), new Location(13, 11), 1);
		addLane("Edge_1311_1411", new Location(13, 11), new Location(14, 11), 1);
		addLane("Edge_1411_1511", new Location(14, 11), new Location(15, 11), 1);
		addLane("Edge_1510_1511", new Location(15, 10), new Location(15, 11), 1);
		addLane("Edge_1511_1611", new Location(15, 11), new Location(16, 11), 1);
		addLane("Edge_1611_1711", new Location(16, 11), new Location(17, 11), 1);
		addLane("Edge_1711_1811", new Location(17, 11), new Location(18, 11), 1);
		
		//13th line
		addLane("Edge_311_312", new Location(3, 11), new Location(3, 12), 1);
		addLane("Edge_711_712", new Location(7, 11), new Location(7, 12), 1);
		addLane("Edge_1111_1112", new Location(11, 11), new Location(11, 12), 1);
		addLane("Edge_1511_1512", new Location(15, 11), new Location(15, 12), 1);
		addLane("Edge_1811_1812", new Location(18, 11), new Location(18, 12), 1);
		
		//14th line
		addLane("Edge_312_313", new Location(3, 12), new Location(3, 13), 1);
		addLane("Edge_712_713", new Location(7, 12), new Location(7, 13), 1);
		addLane("Edge_1112_1113", new Location(11, 12), new Location(11, 13), 1);
		addLane("Edge_1512_1513", new Location(15, 12), new Location(15, 13), 1);
		addLane("Edge_1812_1813", new Location(18, 12), new Location(18, 13), 1);
		
		//15th line
		addLane("Edge_014_114", new Location(0, 14), new Location(1, 14), 1);
		addLane("Edge_114_214", new Location(1, 14), new Location(2, 14), 1);
		addLane("Edge_214_314", new Location(2, 14), new Location(3, 14), 1);
		addLane("Edge_313_314", new Location(3, 13), new Location(3, 14), 1);
		addLane("Edge_314_414", new Location(3, 14), new Location(4, 14), 1);
		addLane("Edge_414_514", new Location(4, 14), new Location(5, 14), 1);
		addLane("Edge_514_614", new Location(5, 14), new Location(6, 14), 1);
		addLane("Edge_614_714", new Location(6, 14), new Location(7, 14), 1);
		addLane("Edge_713_714", new Location(7, 13), new Location(7, 14), 1);
		addLane("Edge_714_814", new Location(7, 14), new Location(8, 14), 1);
		addLane("Edge_814_914", new Location(8, 14), new Location(9, 14), 1);
		addLane("Edge_914_1014", new Location(9, 14), new Location(10, 14), 1);
		addLane("Edge_1014_1114", new Location(10, 14), new Location(11, 14), 1);
		addLane("Edge_1113_1114", new Location(11, 13), new Location(11, 14), 1);
		addLane("Edge_1114_1214", new Location(11, 14), new Location(12, 14), 1);
		addLane("Edge_1214_1314", new Location(12, 14), new Location(13, 14), 1);
		addLane("Edge_1314_1414", new Location(13, 14), new Location(14, 14), 1);
		addLane("Edge_1414_1514", new Location(14, 14), new Location(15, 14), 1);
		addLane("Edge_1513_1514", new Location(15, 13), new Location(15, 14), 1);
		addLane("Edge_1514_1614", new Location(15, 14), new Location(16, 14), 1);
		addLane("Edge_1614_1714", new Location(16, 14), new Location(17, 14), 1);
		addLane("Edge_1714_1814", new Location(17, 14), new Location(18, 14), 1);
		addLane("Edge_1814_1914", new Location(18, 14), new Location(19, 14), 1);
		
		//16th line
		addLane("Edge_314_315", new Location(3, 14), new Location(3, 15), 1);
		addLane("Edge_714_715", new Location(7, 14), new Location(7, 15), 1);
		addLane("Edge_1114_1115", new Location(11, 14), new Location(11, 15), 1);
		addLane("Edge_1514_1515", new Location(15, 14), new Location(15, 15), 1);
		addLane("Edge_1814_1815", new Location(18, 14), new Location(18, 15), 1);
		
		//17th line
		addLane("Edge_016_116", new Location(0, 16), new Location(1, 16), 1);
		addLane("Edge_116_216", new Location(1, 16), new Location(2, 16), 1);
		addLane("Edge_216_316", new Location(2, 16), new Location(3, 16), 1);
		addLane("Edge_315_316", new Location(3, 15), new Location(3, 16), 1);
		addLane("Edge_715_716", new Location(7, 15), new Location(7, 16), 1);
		addLane("Edge_1115_1116", new Location(11, 15), new Location(11, 16), 1);
		addLane("Edge_1515_1516", new Location(15, 15), new Location(15, 16), 1);
		addLane("Edge_1815_1816", new Location(18, 15), new Location(18, 16), 1);
		
		//18th line
		addLane("Edge_316_317", new Location(3, 16), new Location(3, 17), 1);
		addLane("Edge_716_717", new Location(7, 16), new Location(7, 17), 1);
		addLane("Edge_717_817", new Location(7, 17), new Location(8, 17), 1);
		addLane("Edge_817_917", new Location(8, 17), new Location(9, 17), 1);
		addLane("Edge_917_1017", new Location(9, 17), new Location(10, 17), 1);
		addLane("Edge_1017_1117", new Location(10, 17), new Location(11, 17), 1);
		addLane("Edge_1116_1117", new Location(11, 16), new Location(11, 17), 1);
		addLane("Edge_1117_1217", new Location(11, 17), new Location(12, 17), 1);
		addLane("Edge_1217_1317", new Location(12, 17), new Location(13, 17), 1);
		addLane("Edge_1317_1417", new Location(13, 17), new Location(14, 17), 1);
		addLane("Edge_1417_1517", new Location(14, 17), new Location(15, 17), 1);
		addLane("Edge_1516_1517", new Location(15, 16), new Location(15, 17), 1);
		addLane("Edge_1517_1617", new Location(15, 17), new Location(16, 17), 1);
		addLane("Edge_1617_1717", new Location(16, 17), new Location(17, 17), 1);
		addLane("Edge_1717_1817", new Location(17, 17), new Location(18, 17), 1);
		
		//19th line
		addLane("Edge_317_318", new Location(3, 17), new Location(3, 18), 1);
		addLane("Edge_717_718", new Location(7, 17), new Location(7, 18), 1);
		addLane("Edge_1117_1118", new Location(11, 17), new Location(11, 18), 1);
		addLane("Edge_1517_1518", new Location(15, 17), new Location(15, 18), 1);
		addLane("Edge_1817_1818", new Location(18, 17), new Location(18, 18), 1);
		
		//20th line
		addLane("Edge_019_119", new Location(0, 19), new Location(1, 19), 1);
		addLane("Edge_119_219", new Location(1, 19), new Location(2, 19), 1);
		addLane("Edge_219_319", new Location(2, 19), new Location(3, 19), 1);
		addLane("Edge_318_319", new Location(3, 18), new Location(3, 19), 1);
		addLane("Edge_319_419", new Location(3, 19), new Location(4, 19), 1);
		addLane("Edge_419_519", new Location(4, 19), new Location(5, 19), 1);
		addLane("Edge_519_619", new Location(5, 19), new Location(6, 19), 1);
		addLane("Edge_619_719", new Location(6, 19), new Location(7, 19), 1);
		addLane("Edge_718_719", new Location(7, 18), new Location(7, 19), 1);
		addLane("Edge_719_819", new Location(7, 19), new Location(8, 19), 1);
		addLane("Edge_819_919", new Location(8, 19), new Location(9, 19), 1);
		addLane("Edge_919_1019", new Location(9, 19), new Location(10, 19), 1);
		addLane("Edge_1019_1119", new Location(10, 19), new Location(11, 19), 1);
		addLane("Edge_1118_1119", new Location(11, 18), new Location(11, 19), 1);
		addLane("Edge_1119_1219", new Location(11, 19), new Location(12, 19), 1);
		addLane("Edge_1219_1319", new Location(12, 19), new Location(13, 19), 1);
		addLane("Edge_1319_1419", new Location(13, 19), new Location(14, 19), 1);
		addLane("Edge_1419_1519", new Location(14, 19), new Location(15, 19), 1);
		addLane("Edge_1518_1519", new Location(15, 18), new Location(15, 19), 1);
		addLane("Edge_1519_1619", new Location(15, 19), new Location(16, 19), 1);
		addLane("Edge_1619_1719", new Location(16, 19), new Location(17, 19), 1);
		addLane("Edge_1719_1819", new Location(17, 19), new Location(18, 19), 1);
		addLane("Edge_1819_1919", new Location(18, 19), new Location(19, 19), 1);
	}

	private void addLane(String laneId, Location sourceLocNo,
			Location destLocNo, int duration) {
		 Edge lane = new Edge(laneId,nodes.get((sourceLocNo.x*HospitalModel.GSize)+sourceLocNo.y),
		 nodes.get((destLocNo.x*HospitalModel.GSize)+destLocNo.y), duration );
		 edges.add(lane);
	}
}
