package minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class HomePage extends javax.swing.JFrame {

    public HomePage() {
        initComponents();

        board = new Btn[10][10];
        flags = new Btn[10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Btn b = new Btn(row, col);
                Tarla_jPanel.add(b);
                b.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        Btn b = (Btn) e.getComponent();
                        if (e.getButton() == 1) {
                            solTık(b);
                        } else if (e.getButton() == 3) {
                            if (b.isFlag()) {
                                b.setIcon(new ImageIcon());
                                b.setFlag(false);
                                flagCounter++;
                            } else if (!b.isFlag() && flagCounter > 0) {
                                b.setIcon(new ImageIcon("C:\\Users\\ahmet\\Documents\\NetBeansProjects\\SwingCalisma1\\src\\flag.png"));
                                b.setFlag(true);
                                flagCounter--;
                            }
                        }
                        if (boardCounter <= 0) {
                            System.out.println("Tebrikler Kazandınız");
                            showBoard();
                        }
                    }
                });
                board[row][col] = b;
            }
        }

        mayinYerlestir();
        mineCounter();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tarla_jPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mayin Tarlasi");
        setBounds(new java.awt.Rectangle(400, 100, 800, 800));

        Tarla_jPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tarla_jPanel.setLayout(new java.awt.GridLayout(10, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tarla_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tarla_jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public Btn[][] board;
    public Btn[] flags;
    public int flagCounter = 10, boardCounter = 90;

    public void solTık(Btn b) {
        if (b.isMine()) {
            JOptionPane.showMessageDialog(this, "Yandınız oyun bitti.");
            showBoard();
        } else {
            openBoard(b.getRow(), b.getCol());
        }
    }

    public void openBoard(int row, int col) {
        if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col].isMine() || !board[row][col].isEnabled()) {
            return;
        }
        if (row < board.length && row >= 0 && col < board[0].length && col >= 0 && board[row][col].getCount() > 0) {
            board[row][col].setText(board[row][col].getCount() + "");
            boardCounter--;
            board[row][col].setEnabled(false);
            return;
        }
        if (board[row][col].getCount() == 0) {
            board[row][col].setText("");
            board[row][col].setEnabled(false);
            boardCounter--;
        }
        openBoard(row - 1, col);
        openBoard(row, col - 1);
        openBoard(row, col + 1);
        openBoard(row + 1, col);

    }

    public void mayinYerlestir() {

        int i = 10, randCol, randRow;
        while (i > 0) {
            randRow = (int) (Math.random() * board.length);
            randCol = (int) (Math.random() * board[0].length);
            if (!board[randRow][randCol].isMine()) {
                board[randRow][randCol].setMine(true);
                i--;
            }
        }
    }

    public void showBoard() {

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].isMine()) {
                    board[r][c].setIcon(new ImageIcon("C:\\Users\\ahmet\\Documents\\NetBeansProjects\\SwingCalisma1\\src\\mine.png"));
                } else {
                    board[r][c].setText(board[r][c].getCount() + "");
                }
            }
        }
    }

    public void mineCounter() {

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].isMine()) {
                    minePlusCounter(r, c);
                }
            }
        }

    }

    public void minePlusCounter(int row, int col) {

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                    board[r][c].setCount(board[r][c].getCount() + 1);
                }
            }
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Tarla_jPanel;
    // End of variables declaration//GEN-END:variables

}
