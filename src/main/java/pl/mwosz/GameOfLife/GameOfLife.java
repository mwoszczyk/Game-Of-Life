package pl.mwosz.GameOfLife;

import java.util.Random;

public class GameOfLife {

    private int[][]  tab;
    private int n;
    private GameInterface view;

    public GameOfLife(int[][] tab) {
        this.tab = tab;
        n = tab.length;
    }

    public int getN() {
        return n;
    }

    public void setView(GameInterface gameInterface) {
        this.view = gameInterface;
    }

    public void gameOn() {
        boolean flag = true;
        while (flag) {

            System.out.println(this);
            //todo: Optional zamiast ifa
            if (view != null) {
                view.gameUpdate(tab);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            flag = false;
            int[][] nextTab = new int[n][n];
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    int lifeCounter = 0;
                    if (i - 1 >= 0 && j - 1 >= 0 && tab[i - 1][j - 1] != 0) {
                        lifeCounter++;
                    }
                    if (i - 1 >= 0 && tab[i - 1][j] != 0) {
                        lifeCounter++;
                    }
                    if (i - 1 >= 0 && j + 1 < n && tab[i - 1][j + 1] != 0) {
                        lifeCounter++;
                    }
                    if (j - 1 >= 0 && tab[i][j - 1] != 0) {
                        lifeCounter++;
                    }
                    if (j + 1 < n && tab[i][j + 1] != 0) {
                        lifeCounter++;
                    }
                    if (i + 1 < n && j - 1 >= 0 && tab[i + 1][j - 1] != 0) {
                        lifeCounter++;
                    }
                    if (i + 1 < n && tab[i + 1][j] != 0) {
                        lifeCounter++;
                    }
                    if (i + 1 < n && j + 1 < n && tab[i + 1][j + 1] != 0) {
                        lifeCounter++;
                    }

                    if (lifeCounter == 2) {
                        nextTab[i][j] = tab[i][j];
                    } else if (lifeCounter == 3) {
                        nextTab[i][j] = 1;
                    } else {
                        nextTab[i][j] = 0;
                    }

                    if (nextTab[i][j] > 0) {
                        flag = true;
                    }
                }
            }
            tab = nextTab;
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                s.append("+----");
            }
            s.append("+\n");
            for (int j = 0; j < tab[i].length; j++) {
                s.append("|");
                if (tab[i][j] == 1) {
                    s.append("####");
                } else {
                    s.append("    ");
                }
            }
            s.append("|\n");
        }
        for (int i = 0 ; i < tab.length; i++) {
            s.append("+----");
        }
        s.append("+\n");
        s.append("\n");
        s.append("\n");

        return s.toString();
    }

    public static void main(String[] args) {
        int[][] tab = new int[10][10];
        Random rand = new Random();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = rand.nextInt(2);
            }
        }

        GameOfLife gameOfLife = new GameOfLife(tab);
        gameOfLife.gameOn();
    }
}

