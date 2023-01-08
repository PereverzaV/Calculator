package com.shpp.p2p.cs.vpereverza.assignment11;

import com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1;

public class test {
    public static void main(String[] args) {
        int step = 1;
        String[] expression;

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"(-2,2+2.2)+7", "a=2"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : 7");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------" );
        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"-a / -A", "a=2"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : 1");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"-a / -a", "A=2"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : 1");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"1/(sqrt(25) -4)"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : 1");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"1/(a^-2 - 0.5)", "a=4"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : -2.2857");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"cos(1 + 2^2 - 5*a) + 3^2", "a=1"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : 10");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Test №" + step);
        expression = new String[]{"-a-b*cos((-a-b)*5)", "a=1", "b=-1"};
        System.out.println("Mathematical expression : " + expression[0]);
        System.out.println("Expected result : 0");
        com.shpp.p2p.cs.vpereverza.assignment11.Assignment11Part1.main(expression);
        Assignment11Part1.i=0;
        step++;
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println();
    }
}
