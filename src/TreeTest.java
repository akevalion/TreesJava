

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeTest{
    private Tree<Integer> [] trees;
    /**
Por ejemplo:
                    10.                   Niv.0
          7.                    13.      Niv.1
   5.           8.      12.        14 Niv.2

Niveles, 0, 1 y 2 
Un nivel impar es el Nivel 1...
0 y 2 son niveles pares.*/
    @BeforeEach
    public void setUp(){
        int[] numbers = {10, 7, 13, 5, 8, 12, 14};
        trees = new Tree[numbers.length];
        for(int k = 0; k < numbers.length; k++)
            trees[k] = new Tree(numbers[k]);
        trees[0].left = trees[1];
        trees[0].right = trees[2];
        
        trees[1].left = trees[3];
        trees[1].right = trees[4];
        
        trees[2].left = trees[5];
        trees[2].right = trees[6];
    }
    @Test
    public void testPrint(){
        PrintVisitor<Integer> visitor = new PrintVisitor();
        trees[4].right = new Tree(20);
        trees[0].accept(visitor);
        
        System.out.println(visitor.result);
        assertEquals(visitor.result, 
        "-----10\n"+
        "-7-------13\n"+
        "5-8----12--14\n"+
        "---20 ");
    }
    /**
     * Entonces, la cantidad de nodos del Nivel 1 es 2, que serian el 7 y el 13.
     */
    @Test
    public void testCountOddNodes(){
        OddCountVisitor<Integer> visitor = new OddCountVisitor();
        trees[0].accept(visitor);
        assertEquals(visitor.oddNodes.length, 2);
        assertEquals(visitor.oddNodes[0].data, 7);
        assertEquals(visitor.oddNodes[1].data, 13);
    }
    /**
     * El mismo test pero con otro nivel
     */
    @Test
    public void testCountOddNodesWithExtraLevels(){
        OddCountVisitor<Integer> visitor = new OddCountVisitor();
        trees[4].left = new Tree(44);
        trees[6].right = new Tree(88);
        trees[0].accept(visitor);
        assertEquals(visitor.oddNodes.length, 4);
        assertEquals(visitor.oddNodes[0].data, 7);
        assertEquals(visitor.oddNodes[1].data, 44);
        assertEquals(visitor.oddNodes[2].data, 13);
        assertEquals(visitor.oddNodes[3].data, 88);
    }
}





