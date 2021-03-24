package com.company;

import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions;
    //    static ArrayList<Integer> player2Positions;
    static ArrayList<Integer> cpuPositions;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            playerPositions = new ArrayList<>();
//            player2Positions = new ArrayList<>();
            cpuPositions = new ArrayList<>();
            playTicTacToe();
            System.out.println("Do you want to play Again ? [Y/N]");
            String playAgain = sc.next();
            if (playAgain.equals("N") || playAgain.equals("n")){
                break;
            }
        }while (true);
    }

    public static void playTicTacToe() {
        char[][] board = {  {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '}   };
        printBoard(board);

        while (true) {
            Scanner sc = new Scanner(System.in);
//            System.out.println("Choose X or O");
//            String choose = sc.next();
//            if (choose.equals('X')){
//                char antichoose = '0';
//            }else if (choose.equals('0')){
//                char antichoose = 'X';
//            }
            System.out.println("Where do you wanna place ? Enter position [1-9]");
            int userPos = sc.nextInt();
            if (!(userPos >= 1 && userPos <= 9)){
                System.out.println("Wrong Input");
                userPos = sc.nextInt();
            }
            while (!validPos(userPos)) {
                System.out.println("Position taken, try another Position");
                userPos = sc.nextInt();
            }
            placePiece(board, userPos, "player");

            String res = checkWin();
            if (res.length() > 0) {
                printBoard(board);
                System.out.println(res);
                break;
            }

            //CPU plays *
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            while (!validPos(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(board, cpuPos, "cpu");
            printBoard(board);

            res = checkWin();
            if (res.length() > 0) {
                System.out.println(res);
                break;
            }
        }
    }

    public static boolean validPos(int pos) {
        if (playerPositions.contains(pos) || cpuPositions.contains(pos)) {
            return false;
        }
//        else if (player1Positions.contains(pos) || player2Positions.contains(pos)){
//            return false;
//        }if (player2Positions.contains(pos) || cpuPositions.contains(pos)) {
//            return false;
//        }
        return true;
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] board, int pos, String user) {
        char piece = ' ';
        if (user.equals("player")) {
            piece = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            piece = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                board[0][0] = piece;
                break;
            case 2:
                board[0][2] = piece;
                break;
            case 3:
                board[0][4] = piece;
                break;
            case 4:
                board[2][0] = piece;
                break;
            case 5:
                board[2][2] = piece;
                break;
            case 6:
                board[2][4] = piece;
                break;
            case 7:
                board[4][0] = piece;
                break;
            case 8:
                board[4][2] = piece;
                break;
            case 9:
                board[4][4] = piece;
                break;
        }
    }

    public static String checkWin() {
        List<Integer> row1 = Arrays.asList(1, 2, 3);
        List<Integer> row2 = Arrays.asList(4, 5, 6);
        List<Integer> row3 = Arrays.asList(7, 8, 9);
        List<Integer> col1 = Arrays.asList(1, 4, 7);
        List<Integer> col2 = Arrays.asList(2, 5, 8);
        List<Integer> col3 = Arrays.asList(3, 6, 9);
        List<Integer> diag1 = Arrays.asList(1, 5, 9);
        List<Integer> diag2 = Arrays.asList(7, 5, 3);

        List<List> wins = new ArrayList<List>();
        wins.add(row1);
        wins.add(row2);
        wins.add(row3);
        wins.add(col1);
        wins.add(col2);
        wins.add(col3);
        wins.add(diag1);
        wins.add(diag2);

        String res = "";
        for (List l : wins) {
            if (playerPositions.containsAll(l)) {
                return "Congratulaions, You Won :) !!";
            } else if (cpuPositions.containsAll(l)) {
                return "Better Luck Next Time :( ";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                res = "Tie :| ";
            }

        }
        return res;
    }
}
