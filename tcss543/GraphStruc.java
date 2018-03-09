package tcss543;

import java.util.*;
import java.util.Map.Entry;

//data structure and related functions of the graph
public class GraphStruc {
    // [vertex label,vertex]
    private Map<String, VertexStruc> vertices;
    // [Vertex label,adjacent Vertices]
    private Map<String, LinkedList<VertexStruc>> AdjcentVertices;

    //add vertices
    public GraphStruc(Iterator<String> VertexLabel) {
	vertices = new HashMap<String, VertexStruc>();
	AdjcentVertices = new HashMap<String, LinkedList<VertexStruc>>();
	while (VertexLabel.hasNext()) {
            String labelItem = VertexLabel.next();
            VertexStruc v = new VertexStruc(labelItem);
            vertices.put(labelItem, v);
            AdjcentVertices.put(labelItem, new LinkedList<VertexStruc>());
	}
    }

    public Collection<VertexStruc> CollectVertices() {
	return vertices.values();
    }

    public Collection<VertexStruc> GetAdjacentVertices(VertexStruc v) {
        return GetAdjacentVertices(v.GetLabel());
    }

    public Collection<VertexStruc> GetAdjacentVertices(String VertexLabel) {
        return AdjcentVertices.get(VertexLabel);
    }

    //check whether the vertex exists
    private void CheckVertex(String vertexLabel) {
	if (!vertices.containsKey(vertexLabel))
            throw new RuntimeException("Nonexitent vertex: " + vertexLabel);
    }

    //add edge between two vertices
    public void AddEdge(String from, String to) {
        CheckVertex(from);
	CheckVertex(to);
	AdjcentVertices.get(from).add(vertices.get(to));
	AdjcentVertices.get(to).add(vertices.get(from));
    }

    //compute adjacent degree
    public void ComputeAdjacentDegree() {
	Set<Entry<String, LinkedList<VertexStruc>>> vertexList = AdjcentVertices.entrySet();
	for (Entry<String, LinkedList<VertexStruc>> i : vertexList) {
            String vertexLabel = i.getKey();
            LinkedList<VertexStruc> adjList = i.getValue();
            int AdjcentDegree = adjList.size();
            vertices.get(vertexLabel).SetAdjDegree(AdjcentDegree);
	}
    }

    public void DisplayGraph() {
	System.out.println("Graph");
	Set<Entry<String, LinkedList<VertexStruc>>> adjList = AdjcentVertices.entrySet();
	for (Entry<String, LinkedList<VertexStruc>> i : adjList) {
            System.out.print("key: " + i.getKey() + "; value: " + i.getValue());
            VertexStruc v = vertices.get(i.getKey());
            System.out.print("; adj degree: " + v.GetAdjDegree() + "; sat degree: " + v.GetSatDegree() + "\n");
	}
    }

    @Override
    public String toString() {
	return "graph";
    }

}
