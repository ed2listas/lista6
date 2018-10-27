package lista6;

import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 ** Java Program to Implement Strassen Algorithm
 **/
 
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Strassen
{
    public int[][] multiply_normaly(int[][] a, int[][] b, int N)
    {
    	int[][] c = new int[N][N];
    	
        int i,j;

        for(i=0;i<a.length;i++)
        {
            for(j =0; j<a.length;j++)
            {   c[i][j]=0;
                for(int k = 0;k<a.length;k++)
                {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return c;
        
    }

    public static int [][] strassen(int [][] a, int [][] b)
	{
		int n = a.length;
		int [][] result = new int[n][n];

		if((n%2 != 0 ) && (n !=1))
		{
			int[][] a1, b1, c1;
			int n1 = n+1;
			a1 = new int[n1][n1];
			b1 = new int[n1][n1];
			c1 = new int[n1][n1];

			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
				{
					a1[i][j] =a[i][j];
					b1[i][j] =b[i][j];
				}
			c1 = strassen(a1, b1);
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					result[i][j] =c1[i][j];
			return result;
		}

		if(n == 1)
		{
			result[0][0] = a[0][0] * b[0][0];
		}
		else
		{
			int [][] A11 = new int[n/2][n/2];
			int [][] A12 = new int[n/2][n/2];
			int [][] A21 = new int[n/2][n/2];
			int [][] A22 = new int[n/2][n/2];

			int [][] B11 = new int[n/2][n/2];
			int [][] B12 = new int[n/2][n/2];
			int [][] B21 = new int[n/2][n/2];
			int [][] B22 = new int[n/2][n/2];

			divide(a, A11, 0 , 0);
			divide(a, A12, 0 , n/2);
			divide(a, A21, n/2, 0);
			divide(a, A22, n/2, n/2);

			divide(b, B11, 0 , 0);
			divide(b, B12, 0 , n/2);
			divide(b, B21, n/2, 0);
			divide(b, B22, n/2, n/2);

			int [][] P1 = strassen(add2(A11, A22), add2(B11, B22));
			int [][] P2 = strassen(add2(A21, A22), B11);
			int [][] P3 = strassen(A11, sub2(B12, B22));
			int [][] P4 = strassen(A22, sub2(B21, B11));
			int [][] P5 = strassen(add2(A11, A12), B22);
			int [][] P6 = strassen(sub2(A21, A11), add2(B11, B12));
			int [][] P7 = strassen(sub2(A12, A22), add2(B21, B22));

			int [][] C11 = add2(sub2(add2(P1, P4), P5), P7);
			int [][] C12 = add2(P3, P5);
			int [][] C21 = add2(P2, P4);
			int [][] C22 = add2(sub2(add2(P1, P3), P2), P6);

			copy(C11, result, 0 , 0);
			copy(C12, result, 0 , n/2);
			copy(C21, result, n/2, 0);
			copy(C22, result, n/2, n/2);
		}
		return result;
	}

	public static int [][] add2(int [][] A, int [][] B)
	{
		int n = A.length;

		int [][] result = new int[n][n];

		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
			result[i][j] = A[i][j] + B[i][j];

		return result;
	}

	public static int [][] sub2(int [][] A, int [][] B)
	{
		int n = A.length;

		int [][] result = new int[n][n];

		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
			result[i][j] = A[i][j] - B[i][j];

		return result;
	}

	public static void divide(int[][] p1, int[][] c1, int iB, int jB)
	{
		for(int i1 = 0, i2=iB; i1<c1.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<c1.length; j1++, j2++)
			{
				c1[i1][j1] = p1[i2][j2];
			}
	}

	public static void copy(int[][] c1, int[][] p1, int iB, int jB)
	{
		for(int i1 = 0, i2=iB; i1<c1.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<c1.length; j1++, j2++)
			{
				p1[i2][j2] = c1[i1][j1];
			}
	}

    
      /*  public static void print(int [][] array)
	{
		int n = array.length;

		System.out.println();
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}*/
	
   
    public void start (JFrame screen) 
    {
    	ArrayList<Integer> numbers = new ArrayList<Integer>();
        Strassen s = new Strassen();
        
        Random generate_random_numbers = new Random();
    	
    	for(int i = 0 ; i < 500;i++) {
    		numbers.add(i);
    	}
    	
    	Collections.shuffle(numbers);
  
         int N = 8;
         int[][] A = new int[N][N];
         for (int i = 0; i < N; i++)
             for (int j = 0; j < N; j++)
                 A[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
  
         Collections.shuffle(numbers);
         int[][] B = new int[N][N];
         for (int i = 0; i < N; i++)
             for (int j = 0; j < N; j++)
                 B[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
  
               
        long startStraus = System.nanoTime();
        
        int[][] C = s.strassen(A, B);
 		
 		long endStrauss = System.nanoTime();
 		long dif = (endStrauss - startStraus);
 		System.out.println(String.format(dif + "nanosegundos"));
         
         //long end = System.currentTimeMillis(); //time after end activity
  
         for (int i = 0; i < N; i++)
         {
             for (int j = 0; j < N; j++)
                 System.out.print(C[i][j] +" ");
             System.out.println();
         }
         long startBF = System.nanoTime();
         
         int[][] D = s.multiply_normaly(A, B, N);
         
         long endBF = System.nanoTime();
  		long dif2 = (endBF - startBF);
  		System.out.println(dif2 + " nano segundos");
         
         System.out.println("Testing");
         
         for (int i = 0; i < N; i++)
         {
             for (int j = 0; j < N; j++)
                 System.out.print(D[i][j] +" ");
             System.out.println();
         }
         
         //System.out.printf("%.6f ms%n", (end - start) / 1000d);
         
         JPanel matrix = new JPanel();
         matrix.setBounds(0,-200,1600,1500);
         matrix.setLayout(null);
         JLabel LabelMatrixA = new JLabel(transform_string(A,N));
         JLabel LabelMatrixB = new JLabel(transform_string(B,N));
         JLabel LabelMatrixC = new JLabel(transform_string(C, N));
         JLabel Label_X = new JLabel("X");
         Label_X.setBounds(400,-50,1600,800);
         JLabel Label_igual = new JLabel("=");
         Label_igual.setBounds(900,-50,1600,800);
         LabelMatrixA.setBounds(0,-50,1600,800);
        // LabelMatrixA.setFont(new Font("Dialog", Font.PLAIN, 10));
         matrix.add(LabelMatrixA);
         LabelMatrixB.setBounds(450,-50,1600,800);
         matrix.add(LabelMatrixB);
         matrix.add(LabelMatrixC);
         LabelMatrixC.setBounds(1000,-50,1600,800);
         matrix.add(Label_X);
         matrix.add(Label_igual);
        
         
         Collections.shuffle(numbers);
         
         int N1 = 16;
         int[][] E = new int[N1][N1];
         for (int i = 0; i < N1; i++)
             for (int j = 0; j < N1; j++)
                 E[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
  
         Collections.shuffle(numbers);
         int[][] F = new int[N1][N1];
         for (int i = 0; i < N1; i++)
             for (int j = 0; j < N1; j++)
                 F[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
  
               
        long startStraus1 = System.nanoTime();
        
        int[][] G= s.strassen(E, F);
 		
 		long endStrauss1 = System.nanoTime();
 		long dif3 = (endStrauss - startStraus);
 		System.out.println(String.format(dif3 + "nanosegundos"));
         
         //long end = System.currentTimeMillis(); //time after end activity
  
        /* for (int i = 0; i < N; i++)
         {
             for (int j = 0; j < N; j++)
                 System.out.print(C[i][j] +" ");
             System.out.println();
         }*/
                  long startBF2 = System.nanoTime();
         
         int[][] H = s.multiply_normaly(E, F, N1);
         
        long endBF2 = System.nanoTime();
  		long dif4 = (endBF2 - startBF2);
  		System.out.println(dif4 + " nano segundos");
         
         /*for (int i = 0; i < N; i++)
         {
             for (int j = 0; j < N; j++)
                 System.out.print(D[i][j] +" ");
             System.out.println();
         }*/
  		
  		JLabel LabelMatrixE = new JLabel(transform_string(E,N1));
        JLabel LabelMatrixF = new JLabel(transform_string(F,N1));
        JLabel LabelMatrixG = new JLabel(transform_string(G, N1));
        JLabel Label_X1 = new JLabel("X");
        Label_X1.setBounds(560,200,1600,800);
        JLabel Label_igual1 = new JLabel("=");
        Label_igual1.setBounds(900,200,1600,800);
        LabelMatrixE.setBounds(0,200,1600,800);
        LabelMatrixE.setFont(new Font("Dialog", Font.PLAIN, 9));
        matrix.add(LabelMatrixE);
        LabelMatrixF.setBounds(600,200,1600,800);
        LabelMatrixF.setFont(new Font("Dialog", Font.PLAIN, 9));
        matrix.add(LabelMatrixF);
        //matrix.add(LabelMatrixG);
        //LabelMatrixG.setBounds(900,300,1600,800);
        //LabelMatrixG.setFont(new Font("Dialog", Font.PLAIN, 8));
        matrix.add(Label_X1);
        matrix.add(Label_igual1);
        
        Collections.shuffle(numbers);
        
        int N2 = 22;
        int[][] I = new int[N2][N2];
        for (int i = 0; i < N2; i++)
            for (int j = 0; j < N2; j++)
                I[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
 
        Collections.shuffle(numbers);
        int[][] J = new int[N2][N2];
        for (int i = 0; i < N2; i++)
            for (int j = 0; j < N2; j++)
                J[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
 
              
       long startStraus2 = System.nanoTime();
       
       int[][] K = s.strassen(I, J);
		
		long endStraus2 = System.nanoTime();
		long dif5 = (endStraus2 - startStraus2);
		System.out.println(String.format(dif5 + "nanosegundos"));
        
        //long end = System.currentTimeMillis(); //time after end activity
 
       /* for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(C[i][j] +" ");
            System.out.println();
        }*/
        long startBF3 = System.nanoTime();
        
        int[][] L = s.multiply_normaly(I, J, N2);
        
       long endBF3 = System.nanoTime();
 		long dif6 = (endBF3 - startBF3);
 		System.out.println(dif6 + " nano segundos");
        
        /*for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(D[i][j] +" ");
            System.out.println();
        }*/
 		
 		JLabel LabelMatrixI = new JLabel(transform_string(I,N2));
       JLabel LabelMatrixJ = new JLabel(transform_string(J,N2));
       JLabel LabelMatrixK = new JLabel(transform_string(K, N2));
       JLabel Label_X2 = new JLabel("X");
       Label_X2.setBounds(770,550,1600,800);
       //JLabel Label_igual2 = new JLabel("=");
       //Label_igual2.setBounds(900,270,1600,800);
       LabelMatrixI.setBounds(100,550,1600,800);
       LabelMatrixI.setFont(new Font("Dialog", Font.PLAIN, 8));
       matrix.add(LabelMatrixI);
       LabelMatrixJ.setBounds(800,550,1600,800);
       LabelMatrixJ.setFont(new Font("Dialog", Font.PLAIN, 8));
       matrix.add(LabelMatrixJ);
       //matrix.add(LabelMatrixG);
       //LabelMatrixG.setBounds(900,300,1600,800);
       //LabelMatrixG.setFont(new Font("Dialog", Font.PLAIN, 8));
       matrix.add(Label_X2);
       //matrix.add(Label_igual1);
        screen.add(matrix);
 
    }
    
public String transform_string(int[][] A, int N) {
    	String matrixA = "";
    	
    	matrixA += "<html>";
    	for (int i = 0; i < N; i++) {
        	matrixA += "| ";
            for (int j = 0; j < N; j++) {
        		
        		if(Integer.toString(A[i][j]).length() < 4) {
        			for(int k = 0 ; k < (4 - Integer.toString(A[i][j]).length()); k++) {
        				matrixA += "0";
        			}
        			matrixA += Integer.toString(A[i][j]);
        			matrixA += " | ";
        		}
        		else {
        			matrixA += Integer.toString(A[i][j]);
        			matrixA += " | ";
        		}
            }
            matrixA += " <br/> ";
        }
        matrixA += "</html>";
    	
    	return matrixA;
    }
    
}
