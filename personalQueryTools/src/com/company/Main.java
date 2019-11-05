package com.company;

import com.company.service.CommandParser;
import com.company.service.IExecute;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true){

            Scanner scanner = new Scanner(System.in);
            System.out.println("please input the command and args!");

            String commands = scanner.nextLine();

            IExecute execute = CommandParser.parseArgs(commands);

            try {
                if (execute != null){
                    execute.execute(commands);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
