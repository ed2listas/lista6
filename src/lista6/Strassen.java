package lista6;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 ** Java Program to Implement Strassen Algorithm
 **/
 
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Strassen
{
	
	public static int cont;
	public static int cont1;
	
	public Strassen() {
		this.cont = 0;
		this.cont1 = 0;
	} 
	
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
                    this.cont++;
                }
            }
        }
        
        return c;
        
    }

    public int [][] strassen(int [][] a, int [][] b)
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
			this.cont1++;
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
 		String diff_text = ("Algoritimo strassen em matriz 8x8 excutou em: "+ dif + " nanosegundos"); 
 		//long end = System.currentTimeMillis(); //time after end activity
 		
 		
         /*for (int i = 0; i < N; i++)
         {
             for (int j = 0; j < N; j++)
                 System.out.print(C[i][j] +" ");
             System.out.println();
         }*/
 		
        long startBF = System.nanoTime();
         
        int[][] D = s.multiply_normaly(A, B, N);
           
        long endBF = System.nanoTime();
  		long dif2 = (endBF - startBF);
  		String diff2_text = ("Algoritimo força bruta em matriz 8x8 excutou em:"+ dif2 + "nanosegundos");
        
         
        /* for (int i = 0; i < N; i++)
         {
             for (int j = 0; j < N; j++)
                 System.out.print(D[i][j] +" ");
             System.out.println();
         }*/
         
         //System.out.printf("%.6f ms%n", (end - start) / 1000d);
         
         JPanel matrix = new JPanel();
         matrix.setBounds(0,-200,1600,1500);
         matrix.setLayout(null);
         JLabel LabelMatrixA = new JLabel(transform_string(A,N));
         JLabel LabelMatrixB = new JLabel(transform_string(B,N));
         JLabel LabelMatrixC = new JLabel(transform_string(C, N));
         JLabel nMultStra = new JLabel("|  Numeros de multiplicações strassen: "+ cont1);
         JLabel nMultBT = new JLabel("|  Numeros de multiplicações : "+ cont);
         JLabel Label_X = new JLabel("X");
         Label_X.setBounds(470,-50,1600,800);
         //JLabel Label_igual = new JLabel("=");
         //Label_igual.setBounds(900,-50,1600,800);
         LabelMatrixA.setBounds(100,-50,1600,800);
         //LabelMatrixA.setFont(new Font("Dialog", Font.PLAIN, 10));
         matrix.add(LabelMatrixA);
         LabelMatrixB.setBounds(500,-50,1600,800);
         matrix.add(LabelMatrixB);
         LabelMatrixC.setBounds(100,-50,1600,800);
         nMultStra.setBounds(610,50,1600,800);
         matrix.add(nMultStra);
         nMultBT.setBounds(610,70,1600,800);
         matrix.add(nMultBT);
         matrix.add(Label_X);
         //matrix.add(Label_igual);
         
         JButton randomize = new JButton("Randomize");
 		randomize.setBounds(1600,50, 200, 30);
 		matrix.add(randomize);
         
         JButton matrix8x8_result = new JButton("Mostrar resultado");
         matrix8x8_result.setBounds(1000,440,200,30);
         matrix.add(matrix8x8_result);
         
         JButton matrix8x8_back = new JButton("Ver matrizes");
         matrix8x8_back.setBounds(1000,440,200,30);
         matrix.add(matrix8x8_back);
         
         matrix.add(LabelMatrixC);
		 matrix.add(matrix8x8_back);
		 LabelMatrixC.setVisible(false);
		 matrix8x8_back.setVisible(false);
         
         matrix8x8_result.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix8x8_result.setVisible(false);
				LabelMatrixA.setVisible(false);
				LabelMatrixB.setVisible(false);
				Label_X.setVisible(false);
				LabelMatrixC.setVisible(true);
				matrix8x8_back.setVisible(true);
				
			}
		});
         
         matrix8x8_back.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				LabelMatrixC.setVisible(false);
 				matrix8x8_back.setVisible(false);
 				matrix8x8_result.setVisible(true);
 				LabelMatrixA.setVisible(true);
 				LabelMatrixB.setVisible(true);
 				Label_X.setVisible(true);
 				
 			}
 		});
         
         this.cont = 0;
         this.cont1 = 0;
         
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
  
               
        long startStrauss2 = System.nanoTime();
        
        int[][] G= s.strassen(E, F);
 		
 		long endStrauss2 = System.nanoTime();
 		long dif3 = (endStrauss2 - startStrauss2);
 		String diff3_text = ("Algoritimo strassen em matriz 16x16 excutou em: "+ dif3 + " nanosegundos");
         
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
  		String diff4_text = ("Algoritimo força bruta em matriz 16x16 excutou em: "+ dif4 + " nanosegundos");
         
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
        Label_X1.setBounds(660,210,1600,800);
        JLabel Label_igual1 = new JLabel("=");
        Label_igual1.setBounds(900,210,1600,800);
        LabelMatrixE.setBounds(100,210,1600,800);
        LabelMatrixE.setFont(new Font("Dialog", Font.PLAIN, 9));
        matrix.add(LabelMatrixE);
        LabelMatrixF.setBounds(700,210,1600,800);
        LabelMatrixF.setFont(new Font("Dialog", Font.PLAIN, 9));
        JLabel nMultStra1 = new JLabel("|  Numeros de multiplicações strassen: "+ cont1);
        JLabel nMultBT1 = new JLabel("|  Numeros de multiplicações : "+ cont);
        matrix.add(LabelMatrixF);
        LabelMatrixG.setBounds(100,210,1600,800);
        //LabelMatrixG.setFont(new Font("Dialog", Font.PLAIN, 8));
        nMultStra1.setBounds(650,360,1600,800);
        matrix.add(nMultStra1);
        nMultBT1.setBounds(650,380,1600,800);
        matrix.add(nMultBT1);
        matrix.add(Label_X1);
        //matrix.add(Label_igual1);
        
        JButton matrix16x16_result = new JButton("Mostrar resultado");
        matrix16x16_result.setBounds(1000,760,200,30);
        matrix.add(matrix16x16_result);
        
        JButton matrix16x16_back = new JButton("Ver matrizes");
        matrix16x16_back.setBounds(1000,760,200,30);
        matrix.add(matrix16x16_back);
        
        matrix.add(LabelMatrixG);
        LabelMatrixG.setFont(new Font("Dialog", Font.PLAIN, 9));
        LabelMatrixG.setVisible(false);
        matrix16x16_back.setVisible(false);
        
        matrix16x16_result.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix16x16_result.setVisible(false);
				LabelMatrixE.setVisible(false);
				LabelMatrixF.setVisible(false);
				Label_X1.setVisible(false);
				LabelMatrixG.setVisible(true);
				matrix16x16_back.setVisible(true);
				
			}
		});
        
        matrix16x16_back.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				LabelMatrixG.setVisible(false);
 				matrix16x16_back.setVisible(false);
 				matrix16x16_result.setVisible(true);
 				LabelMatrixE.setVisible(true);
 				LabelMatrixF.setVisible(true);
 				Label_X1.setVisible(true);
 				
 			}
 		});
        
        this.cont = 0;
        this.cont1 = 0;
        
        System.out.println(cont);
        System.out.println(cont1);
        
        Collections.shuffle(numbers);
        
        int N2 = 26;
        int[][] I = new int[N2][N2];
        for (int i = 0; i < N2; i++)
            for (int j = 0; j < N2; j++)
                I[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
 
        Collections.shuffle(numbers);
        int[][] J = new int[N2][N2];
        for (int i = 0; i < N2; i++)
            for (int j = 0; j < N2; j++)
                J[i][j] = numbers.get(generate_random_numbers.nextInt(100));;
 
              
       long startStraus3 = System.nanoTime();
       
       int[][] K = s.strassen(I, J);
		
		long endStraus3 = System.nanoTime();
		long dif5 = (endStraus3 - startStraus3);
		String diff5_text = ("Algoritimo strassen em matriz 26x26 excutou em: "+ dif5 + " nanosegundos");
        
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
 		long diff6 = (endBF3 - startBF3);
 		String diff6_text = ("Algoritimo força bruta em matrix 26x26 executou em: " + diff6 + " nanosegundos");
        
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
       JLabel Label_diff1 = new JLabel(diff_text);
       JLabel Label_diff2 = new JLabel(diff2_text);
       JLabel Label_diff3 = new JLabel(diff3_text);
       JLabel Label_diff4 = new JLabel(diff4_text);
       JLabel Label_diff5 = new JLabel(diff5_text);
       JLabel Label_diff6 = new JLabel(diff6_text);
       JLabel nMultStra2 = new JLabel("|  Numeros de multiplicações strassen: "+ cont1);
       JLabel nMultBT2 = new JLabel("|  Numeros de multiplicações : "+ cont);
       Label_X2.setBounds(870,550,1600,800);
       //JLabel Label_igual2 = new JLabel("=");
       //Label_igual2.setBounds(900,270,1600,800);
       LabelMatrixI.setBounds(100,550,1600,800);
       LabelMatrixI.setFont(new Font("Dialog", Font.PLAIN, 8));
       matrix.add(LabelMatrixI);
       LabelMatrixJ.setBounds(900,550,1600,800);
       LabelMatrixJ.setFont(new Font("Dialog", Font.PLAIN, 8));
       matrix.add(LabelMatrixJ);
       //matrix.add(LabelMatrixG);
       //LabelMatrixG.setFont(new Font("Dialog", Font.PLAIN, 8));
       matrix.add(Label_X2);
       //matrix.add(Label_igual1);
       Label_diff1.setBounds(100,50,1600,800);
       matrix.add(Label_diff1);
       Label_diff2.setBounds(100,70,1600,800);
       matrix.add(Label_diff2);
       Label_diff3.setBounds(100,360,1600,800);
       matrix.add(Label_diff3);
       Label_diff4.setBounds(100,380,1600,800);
       matrix.add(Label_diff4);
       Label_diff5.setBounds(100,700,1600,800);
       matrix.add(Label_diff5);
       Label_diff6.setBounds(100,720,1600,800);
       matrix.add(Label_diff6);
       nMultStra2.setBounds(650,700,1600,800);
       matrix.add(nMultStra2);
       nMultBT2.setBounds(650,720,1600,800);
       matrix.add(nMultBT2);
       
       JButton matrix26x26_result = new JButton("Mostrar resultado");
       matrix26x26_result.setBounds(1000,1100,200,30);
       matrix.add(matrix26x26_result);
       
       JButton matrix26x26_back = new JButton("Ver matrizes");
       matrix26x26_back.setBounds(1000,1100,200,30);
       matrix.add(matrix26x26_back);
       
       matrix.add(LabelMatrixK);
       LabelMatrixK.setBounds(100,550,1600,800);
       LabelMatrixK.setFont(new Font("Dialog", Font.PLAIN, 8));
       LabelMatrixK.setVisible(false);
       matrix26x26_back.setVisible(false);
       
       matrix26x26_result.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				matrix26x26_result.setVisible(false);
				LabelMatrixI.setVisible(false);
				LabelMatrixJ.setVisible(false);
				Label_X2.setVisible(false);
				LabelMatrixK.setVisible(true);
				matrix26x26_back.setVisible(true);
				
			}
		});
       
       matrix26x26_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LabelMatrixK.setVisible(false);
				matrix26x26_back.setVisible(false);
				matrix26x26_result.setVisible(true);
				LabelMatrixI.setVisible(true);
				LabelMatrixJ.setVisible(true);
				Label_X2.setVisible(true);
				
			}
		});
              
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
