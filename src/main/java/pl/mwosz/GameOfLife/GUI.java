package pl.mwosz.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUI extends JFrame implements GameInterface {

    private GameOfLife game;
    private Square[][] tabSquare;
    private int n;

    public GUI(GameOfLife gameOfLife) {
        this.game = gameOfLife;
        this.n = game.getN();
        this.game.setView(this);
        tabSquare = new Square[n][n];
        GridLayout layout = new GridLayout(n,n);
        setLayout(layout);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tabSquare[i][j] = new Square();
                add(tabSquare[i][j]);
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(640,640));
        setVisible(true);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {  // sluzy do obslugi dlugich dzialan ingerujacych w tlo || Void - nieinstancjonowany placeHolder dla referencji do klasy
            @Override
            protected Void doInBackground() throws Exception {
                game.gameOn();
                return null;
            }
        };
        worker.execute();
    }

    @Override
    public void gameUpdate(int[][] tabToUpdate) {
        for (int i = 0; i < tabToUpdate.length; i++) {
            for (int j = 0; j < tabToUpdate[i].length; j++) {
                if (tabToUpdate[i][j] != 0) {
                    tabSquare[i][j].setFill(true);
                } else {
                    tabSquare[i][j].setFill(false);
                }
            }
        }
        this.repaint();
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI(gameOfLife);
            }
        });

    }
}

