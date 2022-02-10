/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolverxxl;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

/**
 *
 * @author SweePing
 */
public class SudokuSolverXXL {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        String inputFileName = readInputFile("C:\\Users\\SweePing\\Documents\\Sudoku\\InputFile.txt");
        int[][] grid = readASudoku(inputFileName);
        
        String outputFileName = inputFileName.replaceFirst("([^.]*)(.[^.]+)", "$1" + "_answer" + "$2");

        System.out.println("Sudoku Input: " + "Zero Count: " + CountZero(grid));
        DisplayGrid(grid);
        String[][] possMatrix = findPossibilityGrid(grid);
        displayPossMatrix(possMatrix);
        
        System.out.println("Sudoku Output(Mode 0): " + SolveSudoku(grid, 0)
                           + " Zero Count: " + CountZero(grid));
        DisplayGrid(grid);    
        possMatrix = findPossibilityGrid(grid);
        displayPossMatrix(possMatrix);
        
        if (fixObviousCells(grid, possMatrix)) {
            System.out.println("Zero Count: " + CountZero(grid));
            DisplayGrid(grid);
            possMatrix = findPossibilityGrid(grid);
            displayPossMatrix(possMatrix);
        }
        
        int[][] backup_grid = new int[9][9];
        arrayCopy(grid, backup_grid);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    String str = possMatrix[i][j];
                    String[] str_temp = str.split(":");
                    int n = Integer.valueOf(str_temp[0]);
                    String str1 = str_temp[1];
                    String[] string = str1.replaceAll("\\[", "")
                                         .replaceAll("]", "")
                                         .replaceAll(" ", "")
                                         .split(",");

                    int[] arr = new int[n];
                    for (int k = 0; k < n; k++) {
                        arr[k] = Integer.valueOf(string[k]);
                    }
                    
                    for (int k = 0; k < n; k++) {
                        grid[i][j] = arr[k];
                        if (SolveSudoku(grid, 0)) {
                            i = 9;
                            j = 9;
                            break;
                        } else {
                            int[][] backup_grid_1 = new int[9][9];
                            arrayCopy(grid, backup_grid_1);
                            String[][] possMatrix_1 = findPossibilityGrid(grid);
                            for (int x = 0; x < 9; x++) {
                                for (int y = 0; y < 9; y++) {
                                    if (grid[x][y] == 0) {
                                        String str_1 = possMatrix_1[x][y];
                                        String[] str1_temp = str_1.split(":");
                                        int n1 = Integer.valueOf(str1_temp[0]);
                                        String str1_1 = str1_temp[1];
                                        String[] string_1 = str1_1.replaceAll("\\[", "")
                                                             .replaceAll("]", "")
                                                             .replaceAll(" ", "")
                                                             .split(",");

                                        int[] arr_1 = new int[n1];
                                        for (int k1 = 0; k1 < n1; k1++) {
                                            arr_1[k1] = Integer.valueOf(string_1[k1]);
                                        }

                                        for (int k1 = 0; k1 < n1; k1++) {
                                            grid[x][y] = arr_1[k1];
                                            if (SolveSudoku(grid, 0)) {
                                                x = 9;
                                                y = 9;
                                                break;
                                            } else {                
                                                int[][] backup_grid_2 = new int[9][9];
                                                arrayCopy(grid, backup_grid_2);
                                                String[][] possMatrix_2 = findPossibilityGrid(grid);
                                                for (int x1 = 0; x1 < 9; x1++) {
                                                    for (int y1 = 0; y1 < 9; y1++) {
                                                        if (grid[x1][y1] == 0) {
                                                            String str_2 = possMatrix_2[x1][y1];
                                                            String[] str2_temp = str_2.split(":");
                                                            int n2 = Integer.valueOf(str2_temp[0]);
                                                            String str1_2 = str2_temp[1];
                                                            String[] string_2 = str1_2.replaceAll("\\[", "")
                                                                                 .replaceAll("]", "")
                                                                                 .replaceAll(" ", "")
                                                                                 .split(",");

                                                            int[] arr_2 = new int[n2];
                                                            for (int k2 = 0; k2 < n2; k2++) {
                                                                arr_2[k2] = Integer.valueOf(string_2[k2]);
                                                            }

                                                            for (int k2 = 0; k2 < n2; k2++) {
                                                                grid[x1][y1] = arr_2[k2];
                                                                if (SolveSudoku(grid, 0)) {
                                                                    x1 = 9;
                                                                    y1 = 9;
                                                                    break;
                                                                } else {                                                                    
                                                                    int[][] backup_grid_3 = new int[9][9];
                                                                    arrayCopy(grid, backup_grid_3);
                                                                    String[][] possMatrix_3 = findPossibilityGrid(grid);
                                                                    for (int x2 = 0; x2 < 9; x2++) {
                                                                        for (int y2 = 0; y2 < 9; y2++) {
                                                                            if (grid[x2][y2] == 0) {
                                                                                String str_3 = possMatrix_3[x2][y2];
                                                                                String[] str3_temp = str_3.split(":");
                                                                                int n3 = Integer.valueOf(str3_temp[0]);
                                                                                String str1_3 = str3_temp[1];
                                                                                String[] string_3 = str1_3.replaceAll("\\[", "")
                                                                                                     .replaceAll("]", "")
                                                                                                     .replaceAll(" ", "")
                                                                                                     .split(",");

                                                                                int[] arr_3 = new int[n3];
                                                                                for (int k3 = 0; k3 < n3; k3++) {
                                                                                    arr_3[k3] = Integer.valueOf(string_3[k3]);
                                                                                }

                                                                                for (int k3 = 0; k3 < n3; k3++) {
                                                                                    grid[x2][y2] = arr_3[k3];
                                                                                    if (SolveSudoku(grid, 0)) {
                                                                                        x2 = 9;
                                                                                        y2 = 9;
                                                                                        break;
                                                                                    } else {
                                                                                        arrayCopy(backup_grid_3, grid);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }                                                
                                                                    if (isValid(grid)) {
                                                                        i = 9;
                                                                        j = 9;
                                                                        break;
                                                                    } else {
                                                                        arrayCopy(backup_grid_2, grid);
                                                                    }      
                                                                }
                                                            }
                                                        }
                                                    }
                                                }                                                
                                                if (isValid(grid)) {
                                                    i = 9;
                                                    j = 9;
                                                    break;
                                                } else {
                                                    arrayCopy(backup_grid_1, grid);
                                                }                                                
                                            }
                                        }
                                    }
                                }
                            }
                            if (isValid(grid)) {
                                i = 9;
                                j = 9;
                                break;
                            } else {
                                arrayCopy(backup_grid, grid);                                
                            }                         
                        }
                    }
                }
            }
        }

        System.out.println("Sudoku Output(Iterations): " + " Zero Count: " 
                           + CountZero(grid));
        
        System.out.println(isValid(grid) ? "Valid solution" :
                "Invalid solution");
        DisplayGrid(grid);        
        printGrid(grid, outputFileName);
    }
    
    public static boolean isValid(int[][] grid) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] < 1 || grid[i][j] > 9
                    || !isValid(i, j, grid))
                    return false;
        return true;
    }
    
    public static boolean isValid(int i, int j, int[][] grid) {
        for (int column = 0; column < 9; column++)
            if (column != j && grid[i][column] == grid[i][j])
                return false;
        
        for (int row = 0; row < 9; row++)
            if (row != i && grid[row][j] == grid[i][j])
                return false;
        
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if (!(row == i && col == j) && grid[row][col] == grid[i][j])
                    return false;
        
        return true;
    }
    
    public static void arrayCopy(int[][] source, int[][] destination) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                destination[i][j] = source[i][j];
    }
    
    public static boolean arrayHasZero (int[][] grid) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] == 0)
                    return true;
        
        return false;
    }
    
    public static int[][] readASudoku(String filename) throws FileNotFoundException {
        File file = new File(filename);
        System.out.println("Reading file: " + filename);
        Scanner input = new Scanner(file);
        
        int[][] grid = new int[9][9];
        
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid[i][j] = input.nextInt();            
        
        return grid;
    }
    
    public static String readInputFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);
        
        String InputFileName = input.nextLine();     
        
        return InputFileName;
    }
    
    public static boolean fixObviousCells(int[][] grid, String[][] possMatrix) {
        int fix_count = 0;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                int[] remainingNumbers = new int[10];
                for (int x = 0; x < 10; x++)
                    remainingNumbers[x] = 1;

                for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
                    for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++) {
                        if (grid[row][column] != 0) {
                            remainingNumbers[grid[row][column]] = 0;
                        }                            
                    }                        
                }

                int count = 0;
                for (int x = 1; x < 10; x++)
                    if (remainingNumbers[x] == 1)
                        count++;
                
                if (count == 0)
                    continue;

                int[] variables = new int[count];
                int z = 0;
                for (int x = 1; x < 10; x++)
                    if (remainingNumbers[x] == 1)
                        variables[z++] = x;
                
                for (int y = 0; y < count; y++) {
                    int hit = 0;
                    int row_hit = 9;
                    int column_hit = 9;
                    int number = variables[y];
                    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
                        for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++) {
                            if (grid[row][column] == 0) {
                                String str = possMatrix[row][column];
                                String[] str_temp = str.split(":");
                                int n = Integer.valueOf(str_temp[0]);
                                String str1 = str_temp[1];
                                String[] string = str1.replaceAll("\\[", "")
                                                     .replaceAll("]", "")
                                                     .replaceAll(" ", "")
                                                     .split(",");

                                int[] arr = new int[n];
                                for (int k = 0; k < n; k++) {
                                    arr[k] = Integer.valueOf(string[k]);
                                }
                                
                                for (int k = 0; k < n; k++) {
                                    if (number == arr[k]) {
                                        if (hit == 0) {
                                            row_hit = row;
                                            column_hit = column;
                                        }
                                        hit++;
                                    }
                                }
                            }                            
                        }                        
                    }
                    if (hit == 1) {
                        grid[row_hit][column_hit] = number;
                        fix_count++;
                    }
                }
            }
        }
        System.out.println("Fixed " + fix_count + " obvious numbers.");
        if (fix_count == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isProblematic(int[][] grid) {
        int[] possInt = new int[10];
        int count;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    for (int k = 0; k <= 9; k++) {
                        possInt[k] = 1;
                    }
                    for (int column = 0; column < 9; column++)
                        if (column != j && grid[i][column] != 0) {
                            possInt[grid[i][column]] = 0;
                        }

                    for (int row = 0; row < 9; row++)
                        if (row != i && grid[row][j] != 0) {
                            possInt[grid[row][j]] = 0;                           
                        }

                    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
                        for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++)
                            if (!(row == i && column == j) && grid[row][column] != 0) {
                                possInt[grid[row][column]] = 0; 
                            }    
                    
                    count = 0;
                    for (int k = 1; k <= 9; k++) {
                        if (possInt[k] == 1) {
                            count++;
                        }
                    }
                    
                    if (count == 0) {
                        return true;
                    }
                }   
            }
        }
        return false;
    }

    public static String[][] findPossibilityGrid(int[][] grid) {
        String[][] possMatrix = new String[9][9];
        int[] possInt = new int[10];
        int count;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    for (int k = 0; k <= 9; k++) {
                        possInt[k] = 1;
                    }
                    for (int column = 0; column < 9; column++)
                        if (column != j && grid[i][column] != 0) {
                            possInt[grid[i][column]] = 0;
                        }

                    for (int row = 0; row < 9; row++)
                        if (row != i && grid[row][j] != 0) {
                            possInt[grid[row][j]] = 0;                           
                        }

                    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
                        for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++)
                            if (!(row == i && column == j) && grid[row][column] != 0) {
                                possInt[grid[row][column]] = 0; 
                            }    
                    
                    int value = 0;
                    count = 0;
                    for (int k = 1; k <= 9; k++) {
                        if (possInt[k] == 1) {
                            count++;
                            value = k;
                        }
                    }
                    
                    if (count == 0) {
                        possMatrix[i][j] = Integer.toString(count) + ":" + "( )";
                    } else if (count == 1) {
                        possMatrix[i][j] = Integer.toString(count) + ":" + 
                                           Integer.toString(value);
                    } else {
                        int[] possArray = new int[count];
                        int m = 0;
                        for (int k = 1; k <= 9; k++) {
                            if (possInt[k] == 1) {
                                possArray[m] = k;
                                m++;
                            }
                        }                        
                        possMatrix[i][j] = Integer.toString(count) + ":" + 
                                           Arrays.toString(possArray);
                    }
                } else {
                    possMatrix[i][j] = "fixed" + ":" + "<" + grid[i][j] + ">";                    
                }
            }
        }
        return possMatrix;
    }
    
    public static void displayPossMatrix(String[][] possMatrix) {
        int x = possMatrix.length;
        int y = possMatrix.length;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%20s", possMatrix[i][j]);
            }
            System.out.println();
        }        
    }
    
    public static int CountZero(int[][] grid) {
        int count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] == 0)
                    count++;
        
        return count;
    }
    
    public static boolean SolveSudoku(int[][] grid, int mode) {
        // mode = 0: Search for element with single possibility, replace empty
        //           space with the single possible number.
        // mode = 1: Iterative mode. No element is found with single possible
        //           number, need to iterate until single possibility cell can
        //           be found and replaced.
        int[][] backup_grid = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                backup_grid[i][j] = grid[i][j];
        
        int[] possInt = new int[10];
        int count;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    for (int k = 0; k <= 9; k++) {
                        possInt[k] = 1;
                    }
                    for (int column = 0; column < 9; column++)
                        if (column != j && grid[i][column] != 0) {
                            possInt[grid[i][column]] = 0;
                        }

                    for (int row = 0; row < 9; row++)
                        if (row != i && grid[row][j] != 0) {
                            possInt[grid[row][j]] = 0;                           
                        }

                    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
                        for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++)
                            if (!(row == i && column == j) && grid[row][column] != 0) {
                                possInt[grid[row][column]] = 0; 
                            }    
                    
                    count = 0;
                    int value = 0;
                    for (int k = 1; k <= 9; k++) {
                        if (possInt[k] == 1) {
                            count++;
                            value = k;
                        }
                    }
                    
                    if (mode == 0) {
                        if (count == 1) {
                            grid[i][j] = value;
                            if (!isProblematic(grid)) {
                                i = 0;
                                j = 0;                              
                            } else {
                                grid[i][j] = 0;
                            }
                        }                        
                    } else if (mode == 1) {
                        if (count == 1) {
                            grid[i][j] = value;
                            i = 0;
                            j = 0;
                        }                                   
                    }
                }   
            }
        }
        
        if (!arrayHasZero(grid) && !isProblematic(grid)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void DisplayGrid(int[][] grid) {
        int x = grid.length;
        int y = grid[0].length;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%-2d", grid[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void printGrid(int[][] grid, String outputFileName) throws FileNotFoundException {
        int x = grid.length;
        int y = grid[0].length;
        
        File outputFile = new File(outputFileName);
        System.out.println("Writing results into the file: " + outputFileName);
        PrintWriter output = new PrintWriter(outputFile);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                output.printf("%-2d", grid[i][j]);
            }
            output.println();
        }
        output.close();
    }
    
    public static String[][] checkSudoku(int[][] grid, int[][] newgrid, int n) {
        String[][] possibleSolution = new String[9][9];
        int[] possInt = new int[10];
        int count, onlyOne;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    for (int k = 0; k <= 9; k++) {
                        possInt[k] = 1;
                    }
                    for (int column = 0; column < 9; column++)
                        if (column != j && grid[i][column] != 0) {
                            possInt[grid[i][column]] = 0;
                        }

                    for (int row = 0; row < 9; row++)
                        if (row != i && grid[row][j] != 0) {
                            possInt[grid[row][j]] = 0;                           
                        }

                    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
                        for (int column = (j / 3) * 3; column < (j / 3) * 3 + 3; column++)
                            if (!(row == i && column == j) && grid[row][column] != 0) {
                                possInt[grid[row][column]] = 0; 
                            }    
                    
                    count = 0;
                    onlyOne = 0;
                    for (int k = 1; k <= 9; k++) {
                        if (possInt[k] == 1) {
                            count++;
                            onlyOne = k;
                        }
                    }
                    
                    if (count == 0) {
                        //System.out.println("Hitting wall!");
                        possibleSolution[i][j] = "( )";
                        for (int x = 0; x < 9; x++)
                            for (int y = 0; y < 9; y++)
                                grid[x][y] = newgrid[x][y];
                        i = 0;
                        j = 0;
                        // break;
                    } else if (count == n) {
                        grid[i][j] = onlyOne;
                        if (isProblematic(grid)) {
                            grid[i][j] = 0;
                            possibleSolution[i][j] = Integer.toString(onlyOne);
                        } else {
                            onlyOne = 0;
                            i = 0;
                            j = 0;
                            possibleSolution[i][j] = Integer.toString(grid[i][j]);                            
                        }          
                    } else {
                        int[] intArray = new int[count];
                        int m = 0;
                        for (int k = 1; k < 10; k++) {
                            if (possInt[k] == 1) {
                                intArray[m] = k;
                                m++;
                            }
                        }                       
                        possibleSolution[i][j] = java.util.Arrays.toString(intArray);
                    }     
                } else {
                    possibleSolution[i][j] = Integer.toString(grid[i][j]);
                }          
            }
        }
        
        return possibleSolution;
    }    
}
