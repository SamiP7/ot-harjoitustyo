package logic;

import java.util.*;

public class Table {
    
    public int[][] sudokuTable = new int[9][9];
    private boolean b;
    private int[][] sudokuAnswer;
    private boolean[][] canAdd;
    private HashMap<Integer, Integer> amountLeft = new HashMap<>();
    private ArrayDeque<Integer> previousMove = new ArrayDeque<>();
    
    
    public void addNumber(int y, int x, int number) {
        y--;
        x--;
        if (y < 0 || y > 8 || x < 0 || x > 8 || number < 1 || number > 9) {
            System.out.println("Only numbers between 1-9 allowed here.");
        } else if (!canAdd[y][x]) {
            System.out.println("Part of the original puzzle.");
        } else if (amountLeft.get(number) == 0) {
            System.out.println("All already added.");
        } else {
            if (sudokuTable[y][x] == 0) {
                previousMove.addLast(sudokuTable[y][x]);
                previousMove.addLast(y);
                previousMove.addLast(x);
                amountLeft.put(number, amountLeft.get(number) - 1);
                sudokuTable[y][x] = number;
            } else {
                if (sudokuTable[y][x] != number) {
                    previousMove.addLast(sudokuTable[y][x]);
                    previousMove.addLast(y);
                    previousMove.addLast(x);
                }
                amountLeft.put(sudokuTable[y][x], amountLeft.get(sudokuTable[y][x]) + 1);
                amountLeft.put(number, amountLeft.get(number) - 1);
                sudokuTable[y][x] = number;
            }
        }
        
    }
    
    public void cancelLastMove() {
        if (!previousMove.isEmpty()) {
            int x = previousMove.pollLast();
            int y = previousMove.pollLast();
            int value = previousMove.pollLast();
            sudokuTable[y][x] = value;
        }
    }
    
    public boolean isPuzzleDone() {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (sudokuTable[i][o] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int[][] getTable() {
        return this.sudokuTable;
    }
    
    public int[][] getAnswer() {
        return this.sudokuAnswer;
    }
    
    public HashMap<Integer, Integer> yetToBeAdded() {
        return this.amountLeft;
    }
    
    public void clearTable() {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (canAdd[i][o]) {
                    sudokuTable[i][o] = 0;
                }
            }
        }
        for (int i = 1; i <= 9; i++) {
            amountLeft.put(i, 9);
        }
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (sudokuTable[i][o] != 0) {
                    amountLeft.put(sudokuTable[i][o], amountLeft.get(sudokuTable[i][o]) - 1);
                }
            }
        }
        previousMove.clear();
    }
    
    public void createAnswer() {
        this.sudokuAnswer = new int[9][9];
        this.b = false;
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                sudokuAnswer[i][o] = r.nextInt(9) + 1;
                if (!checkIfCorrectTemp(sudokuAnswer) && b != true) {
                    int h = 1;
                    while (true) {
                        if (h > 9) {
                            b = true;
                            break;
                        }
                        sudokuAnswer[i][o] = h;
                        if (checkIfCorrectTemp(sudokuAnswer) == true) {
                            break;
                        }
                        
                        h++;
                    }
                }
                if (b == true) {
                    this.sudokuAnswer = new int[9][9];
                    i = 0;
                    o = 0;
                    sudokuAnswer[0][0] = r.nextInt(9) + 1;
                    b = false;
                }
            }
            
        }
    }
    
    public void createSudokuFromAnswer() {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                sudokuTable[i][o] = sudokuAnswer[i][o];
                amountLeft.put(i + 1, 9);
            }
        }
        Random r = new Random();
        int delete = r.nextInt(15) + 30;
        while (true) {
            if (delete == 0) {
                break;
            }
            int y = r.nextInt(9);
            int x = r.nextInt(9);
            if (sudokuTable[y][x] != 0) {
                amountLeft.put(sudokuTable[y][x], amountLeft.get(sudokuTable[y][x]) - 1);
                sudokuTable[y][x] = 0;
                delete--;
            }
        }
        for (int i = 1; i <= 9; i++) {
            amountLeft.put(i, 9 - amountLeft.get(i));
        }
        notPartOfTheOriginalTable();
    }
    
    private void notPartOfTheOriginalTable() {
        this.canAdd = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                canAdd[i][o] = true;
            }
        }
        
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                if (sudokuTable[i][o] != 0) {
                    canAdd[i][o] = false;
                }
            }
        }
    }
    
    public boolean checkIfCorrect(int[][] sudokuTable) {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                int h = sudokuTable[i][o];
                if (h < 1 || h > 9) {
                    return false;
                }
                for (int x = 0; x < 9; x++) {
                    if (x != i && sudokuTable[x][o] == h) {
                        return false;
                    }
                    if (x != o && sudokuTable[i][x] == h) {
                        return false;
                    }
                }
                if (i < 3 && o < 3) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i < 3 && o < 6 && o > 2) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 3; k < 6; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i < 3 && o >= 6) {
                    for (int j = 0; j < 3; j++) {
                        for (int k = 6; k < 9; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i < 6 && o < 3 && i > 2) {
                    for (int j = 3; j < 6; j++) {
                        for (int k = 0; k < 3; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i < 6 && o < 6 && o > 2 && i > 2) {
                    for (int j = 3; j < 6; j++) {
                        for (int k = 3; k < 6; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i < 6 && o >= 6 && i > 2) {
                    for (int j = 3; j < 6; j++) {
                        for (int k = 6; k < 9; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i >= 6 && o < 3) {
                    for (int j = 6; j < 9; j++) {
                        for (int k = 0; k < 3; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i >= 6 && o < 6 && o > 2) {
                    for (int j = 6; j < 9; j++) {
                        for (int k = 3; k < 6; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
                if (i >= 6 && o >= 6) {
                    for (int j = 6; j < 9; j++) {
                        for (int k = 6; k < 9; k++) {
                            if (j != i && k != o && sudokuTable[j][k] == h) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public boolean checkIfCorrectTemp(int[][] sudokuTable) {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                int h = sudokuTable[i][o];
                if (h == 0) {
                    continue;
                } else {
                    for (int x = 0; x < 9; x++) {
                        if (x != i && sudokuTable[x][o] == h) {
                            return false;
                        }
                        if (x != o && sudokuTable[i][x] == h) {
                            return false;
                        }
                    }
                    if (i < 3 && o < 3) {
                        for (int j = 0; j < 3; j++) {
                            for (int k = 0; k < 3; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i < 3 && o < 6 && o > 2) {
                        for (int j = 0; j < 3; j++) {
                            for (int k = 3; k < 6; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i < 3 && o >= 6) {
                        for (int j = 0; j < 3; j++) {
                            for (int k = 6; k < 9; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i < 6 && o < 3 && i > 2) {
                        for (int j = 3; j < 6; j++) {
                            for (int k = 0; k < 3; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i < 6 && o < 6 && o > 2 && i > 2) {
                        for (int j = 3; j < 6; j++) {
                            for (int k = 3; k < 6; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i < 6 && o >= 6 && i > 2) {
                        for (int j = 3; j < 6; j++) {
                            for (int k = 6; k < 9; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i >= 6 && o < 3) {
                        for (int j = 6; j < 9; j++) {
                            for (int k = 0; k < 3; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i >= 6 && o < 6 && o > 2) {
                        for (int j = 6; j < 9; j++) {
                            for (int k = 3; k < 6; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                    if (i >= 6 && o >= 6) {
                        for (int j = 6; j < 9; j++) {
                            for (int k = 6; k < 9; k++) {
                                if (j != i && k != o && sudokuTable[j][k] == h) {
                                    return false;
                                }
                            }
                        }
                    }
                } 
            }
        }
        return true;
    }
    
}
