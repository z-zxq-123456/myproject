package com.company;

import com.company.service.CommandParser;
import com.company.service.IExecute;
import com.company.tools.ConfigUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while(ConfigUtils.lastModify==0){
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("*");
        }
        System.out.println("server is started!");

        while (true){

            Scanner scanner = new Scanner(System.in);
            System.out.println("please input the command and args! input help for details");

            String commands = scanner.nextLine();

            IExecute execute = CommandParser.parseArgs(commands);
            try {
                if (execute != null){
                    execute.printLineBefore();

                    execute.execute(commands);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                if (execute != null) execute.printLineAfter();
            }
        }
    }
}
