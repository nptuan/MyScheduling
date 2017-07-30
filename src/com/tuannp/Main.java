package com.tuannp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running My Scheduling");

        //read input data for student and teacher
        List<String[]> listTeacher = readInput("./teachers.txt");
        List<String[]> listStudent = readInput("./students.txt");

        //print input data to console
        System.out.println("\nTeachers: ");
        for (int i=0;i<listTeacher.size();i++) {
            for (int j=0; j<listTeacher.get(i).length; j++) {
                System.out.print(listTeacher.get(i)[j]);
            }
            System.out.println();
        }
        System.out.println("\nStudents: ");
        for (int i=0;i<listStudent.size();i++) {
            for (int j=0; j<listStudent.get(i).length; j++) {
                System.out.print(listStudent.get(i)[j]);
            }
            System.out.println();
        }


        //scheduling algorithm
        for (int i =0; i<listStudent.get(0).length; i++) {
            for (int j=0; j<listStudent.size(); j++) {
                //to ensure every student can attend to 1 session
                for (int k=i; k<listStudent.get(j).length; k++) {
                    if ("1".equals(listStudent.get(j)[k])) {
                        int teacher = findTeacher(k, listTeacher);
                        if (teacher != -1) {
                            System.out.println("Session " + k + " Student " + j + " Teacher " + teacher);
                            break;
                        }
                        else {
                            //can not find teacher for student in this day
                            continue;
                        }
                    }
                    else {
                        //
                        continue;
                    }
                }
            }
        }
    }

    /**
     * find a teacher who is free in @session
     * change value of session to 0 after get the session
     *
     * @param session
     * @param listTeacher
     * @return
     */
    private static int findTeacher(int session, List<String[]> listTeacher) {
        for (int i=0;i<listTeacher.size();i++) {
            if ("1".equals(listTeacher.get(i)[session])) {
                listTeacher.get(i)[session] = "0";
                return i;
            }
        }
        return -1;
    }

    /**
     * read input data from text file
     *
     * first line: number of element (n)
     * line n: value of each session separated by a space. (session represent for 1 week, 1 day or 1 hour)
     *                                  value = 1: free in this session
     *                                  value = 0: busy in this session
     * @param filePath
     * @return List<String[]> 2D array
     */
    private static List<String[]> readInput(String filePath) {
        List<String[]> result = new ArrayList<>();
        int numOfObject = 0;

        try {
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter("");
            if (sc.hasNext()) {
                numOfObject = Integer.parseInt(sc.nextLine());
            }
            for (int i=0;i<numOfObject;i++) {
                if (sc.hasNext()) {
                    String s = sc.nextLine();
                    if (s.trim().isEmpty()) {
                        continue;
                    }
                    result.add(s.split(" "));
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        }
        return result;
    }
}
