package com.example.wsmeverylisten.proxy.jdk.LogTest;

public class love {


    public static void main(String[] args) {


//        System.out.print();

        int life = 0;
        int end = 100;
        int love = 0;

        //直到死之前，每天爱你多一点
        while (life < end) {
            love++;
        }

        System.out.println(life + "x" + end);
        aixin();
    }

    public static void aixin() {

            for (float y=1.5f;y>-1.5f;y-=0.1f){
                for (float x=-1.5f;x<1.5f;x+=0.05f){
                    float a=x*x+y*y-1;
                    if ((a*a*a-x*x*y*y*y)<0.0f){
                        System.out.print("*");
                    }else {
                        System.out.print(" ");
                    }
                }
                System.out.print("\n");
            }
        }

}
