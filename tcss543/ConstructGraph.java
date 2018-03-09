package tcss543;

import java.util.ArrayList;

//This function is used to build the graph
public class ConstructGraph {
    public static GraphStruc ConstructGraph(int numVertices,double density) {

	//Generate Vertices
	ArrayList<String> input = new ArrayList<>();
	for(Integer i=1;i<=numVertices;i++){
            input.add(i.toString());
	}

	//Generate Graph using the vertices
	GraphStruc myGraph = new GraphStruc(input.iterator());

	//add edge
	for(Integer i=1;i<=numVertices;i++){
            for(Integer j=i+1;j<=numVertices;j++){
                //to ensure the density of edges is approximately equal to set
		double RandomPoss = Math.random();
		RandomPoss = (double) Math.round(RandomPoss * 100) / 100;
		if(RandomPoss < density){
                    myGraph.AddEdge(i.toString(),j.toString() );
		}
            }
	}
	myGraph.ComputeAdjacentDegree();
	return myGraph;
	}
}
