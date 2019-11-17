package domain;

import java.util.*;

public class Table {
    
    public int[][] sudokuTable = new int[9][9];
    private boolean b;
    private int[][] sudokuAnswer = new int[9][9];
    
    public void createAnswer() {
        this.b = false;
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                sudokuAnswer[i][o] = r.nextInt(9) + 1;
                if (!checkIfCorrectTemp(sudokuAnswer)) {
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
                    sudokuAnswer = new int[9][9];
                    createAnswer();
                }
            }
            
        }
    }
    
    public int[][] getAnswer() {
        return this.sudokuAnswer;
    }
    
    public void createSudokuFromAnswer() {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                sudokuTable[i][o] = sudokuAnswer[i][o];
            }
        }
        Random r = new Random();
        int delete = r.nextInt(35) + 15;
        while (true) {
            if (delete == 0) {
                break;
            }
            int y = r.nextInt(9);
            int x = r.nextInt(9);
            if (sudokuTable[y][x] != 0) {
                sudokuTable[y][x] = 0;
                delete--;
            }
        }

        
    }
    
    public boolean checkIfCorrect(int[][] sudokuTable) {
        for (int i = 0; i < 9; i++) {
            for (int o = 0; o < 9; o++) {
                int h = sudokuTable[i][o];
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
                }
                else {
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
