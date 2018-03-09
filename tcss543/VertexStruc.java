package tcss543;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//data structure and related functions of the Vertex
public class VertexStruc implements Comparable<VertexStruc> {
    private String label;
    private int AdjDegree;
    private int SaturDegree;
    private int color;
    private Set<Integer> adjcolor;

    public VertexStruc(String label){
	this.label = label;
	AdjDegree=0;
	SaturDegree=0;
	color =0;
        adjcolor = new HashSet<Integer>();
    }

    //comparator: select the vertex to be colored which is with maximal saturation degree in the uncolored subgraph.
    //If there is an equality, choose any vertex of maximal degree
    @Override
    public int compareTo(VertexStruc o) {
        if(this.SaturDegree == o.SaturDegree){
            if(this.AdjDegree > o.AdjDegree){
                return -1;
            } else{
                return 1;
            }
        }
        else if(this.SaturDegree >  o.SaturDegree){
            return -1;
        }
        else{
            return 1;
        }
    }

    public String GetLabel() {
        return label;
    }

    public void SetLabel(String label) {
        this.label = label;
    }

    public int GetAdjDegree() {
        return AdjDegree;
    }

    public void SetAdjDegree(int adjDegree) {
        this.AdjDegree = adjDegree;
    }

    public int GetSatDegree() {
        return SaturDegree;
    }

    public void SetSatDegree(int SaturDegree) {
        this.SaturDegree = SaturDegree;
    }


    public int GetVertexColor() {
        return color;
    }

    public void SetVertexColor(int color) {
        this.color = color;
    }

    public Set<Integer> GetAdjColor(){
        return adjcolor;
    }

    public void SetAdjColor(Set<Integer> color){
        adjcolor = new HashSet<Integer>(color);
    }

    public String toString() {
        return label;
    }
}
