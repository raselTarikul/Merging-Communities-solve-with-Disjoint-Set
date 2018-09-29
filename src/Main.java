import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class DisjointUnionSets {

    int[] rnak, parent;
    int n;
    HashMap<Integer, Integer> counter = new HashMap<>();

    // Constructor
    public DisjointUnionSets(int n){
        parent = new int[n+1];
        rnak = new int[n+1];
        this.n = n;
        makeSet();
    }

    // Creats n ste with single item in each
    void makeSet(){
        for(int i = 1; i <= n; i++ ){
            // Initially all elements are in there own sets
            parent[i] = i;
            counter.put(i, 1);
        }
    }

    // Return representativ of x set
    int find(int x){

        // Find representative of set that contains that x is the element of
        if(parent[x] != x) {
            // if x is not the parent of itself,
            // then x is not representative of
            // his set.
            parent[x] = find(parent[x]);

            // so we recursively call find on its parent
            // move i'th node directly under the
            // representative of its set.
        }
        return parent[x];

    }

    // Union the set that's include x and the set that includes y.
    void union(int x, int y){
        // Find the representative of tow sets.
        int xRoot = find(x);
        int yRoot = find(y);
        int xCount = counter.get(xRoot);
        int yCount = counter.get(yRoot);
        if (xRoot == yRoot){
            return;
        }

        if(rnak[xRoot] > rnak[yRoot]) {
            parent[yRoot] = xRoot;
            counter.replace(xRoot, xCount + yCount);
        }  else {
            parent[xRoot] = yRoot;
            counter.replace(yRoot, xCount + yCount);
            if(rnak[xRoot] == rnak[yRoot]){
                rnak[yRoot] = rnak[yRoot] +1;
            }
        }

    }

    int query(int i){
        int iRoot = find(i);
        return counter.get(iRoot);

    }

}

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String[] str = scanner.nextLine().trim().split(" ");
        Integer n = Integer.parseInt(str[0]);
        Integer q = Integer.parseInt(str[1]);

        String[][] inputes = new String[q][];

        for(int i = 0; i < q ; i++){
            String[] gbRowItems = scanner.nextLine().split(" ");
            inputes[i] = gbRowItems;

        }

        DisjointUnionSets dus = new DisjointUnionSets(n);

        for(int i = 0; i < inputes.length; i ++){
            if(inputes[i][0].equals("M")){
                dus.union(Integer.parseInt(inputes[i][1]), Integer.parseInt(inputes[i][2]));
            } else if(inputes[i][0].equals("Q")){
                System.out.println(dus.query(Integer.parseInt(inputes[i][1])));
            }
        }

    }
}
