package tcss543;

import java.util.*;
import java.util.TreeSet;

public class BrelazDsaturAlg {
    private GraphStruc graph;
    private Set<Integer> colors;
    private Collection<VertexStruc> colorlessVertices;

    //Brelaz's Dsatur Algorithm based on Treeset
    public BrelazDsaturAlg (GraphStruc graph) {
    	this.graph = graph;
	colors = new HashSet<Integer>();
	colorlessVertices = graph.CollectVertices();
    }

    //The total number of color used
    public int getColorCount() {
	return colors.size();
    }

    //Color the graph
    public void colorGraph() {
        TreeSet<VertexStruc> ColorTree = new TreeSet<VertexStruc>();
        ColorTree.addAll(colorlessVertices );//the treeset used to color the graph

        while (colorlessVertices.size() > 0) {   // --- |V|
            VertexStruc ColorNode = ColorTree.last();//the last one is the one with the maximum saturation degree (adjcent degree) --- log|V|
            ColorTree.pollLast();

            //Color the chosen vertex with the least possible(lowestnumbered) color.
            //if all the used color can not be used, add a new color
            Set<Integer> tempColors = new HashSet<Integer>(colors);
            tempColors.removeAll(ColorNode.GetAdjColor());
            int ColorUsed;
            if (tempColors.size() > 0) {
                Object obj = Collections.min(tempColors);
		ColorUsed = (Integer)obj;
            } else {
                ColorUsed = colors.size()+1;
		colors.add(ColorUsed);
            }
            //color
            ColorNode.SetVertexColor(ColorUsed);

            //update: color; the adjcent colors and saturation degree of the neighbors of the selected color   ---   |E|log|V|
            Collection<VertexStruc> adjacentVertices = graph.GetAdjacentVertices(ColorNode);
            for (VertexStruc adjVertex: adjacentVertices) {
                Set<Integer> updateColor = new HashSet<Integer>(adjVertex.GetAdjColor());
                updateColor.add(ColorUsed);
                adjVertex.SetAdjColor(updateColor);
                adjVertex.SetSatDegree(updateColor.size());
            }

            //remove the vertex colored from list
            colorlessVertices.remove(ColorNode);
        }
    }

}
