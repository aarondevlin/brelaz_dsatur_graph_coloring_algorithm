package tcss543;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TCSS543 {
	//set 4 groups with different range of density
    private static class Range {
        Range(double min, double max) {
            this.Max = max;
            this.Min = min;
        }
        double Min;
        double Max;
    }

    public static void main(String[] args) {
        List<Range> ranges = new ArrayList<Range>(4);
	//1st group: range from 0.73 to 0.82
	ranges.add(new Range(0.73, 0.82));
	//2nd group: range from 0.61 to 0.72
	ranges.add(new Range(0.61, 0.72));
	//3rd group: range from 0.44 to 0.59
	ranges.add(new Range(0.44, 0.59));
	//4th group: range from 0.26 to 0.34
	ranges.add(new Range(0.26, 0.34));

        //the maximal number of vertices which will be used run
        int maxVertices = 300;//change this variable to run for desired number of vertices.
	System.out.println("VertexCount,GroupNumber,Colors,Time");
	// generate graph with |V| = 10,20,30 ...
	for (int VerticesNumber = 10; VerticesNumber <= maxVertices; VerticesNumber += 10) {
            //generate graph for each group
            for (int GroupNumber = 0; GroupNumber < 4; GroupNumber++) {
		Range DenRange = ranges.get(GroupNumber);
		int GraphSet = 100;
		//generate 100 groups for each set
		List<GraphStruc> graphs = new ArrayList<GraphStruc>(GraphSet);
		for (int j = 0; j < GraphSet; j++) {
                    // generate a random number in the required range as density
                    double density = GeneRanDens(DenRange.Min, DenRange.Max);
                    graphs.add(ConstructGraph.ConstructGraph(VerticesNumber, density));
		}

		//color the graph by Brelaz's Dsatur Algorithm
		int ColorNumber = 0;
		long BeginTime = System.currentTimeMillis();
		for (GraphStruc graph: graphs) {
                    BrelazDsaturAlg RunAlg = new BrelazDsaturAlg(graph);
                    RunAlg.colorGraph();
                    ColorNumber += RunAlg.getColorCount();
		}
		long EndTime = System.currentTimeMillis();
		long elapsedTime = EndTime - BeginTime;
		System.out.println(String.format("%d, %d, %.3f, %.3f", VerticesNumber, GroupNumber+1, 1.0*ColorNumber/GraphSet, 1.0*elapsedTime/GraphSet));
            }
	}
    }

    //function to generate random number
    private static double GeneRanDens(Double Min, Double Max) {
	double randomNum = 0;
	Random random = new Random();
	randomNum = random.nextDouble() * ((Max - Min) + 0.001) + Min;
	randomNum = (double) Math.round(randomNum * 100) / 100;
	return randomNum;
    }
}
