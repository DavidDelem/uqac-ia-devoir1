package com.company.interfacegraphique;

import javax.swing.*;

public class Fenetre extends JFrame {

    private JLabel[][] imagesPieces;
    private JLabel imageRobot;

    public Fenetre() {
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(815, 845);
        getContentPane().setLayout(null);
        CreateElements();
        setVisible(true);
    }

    private void CreateElements() {
        imagesPieces = new JLabel[10][10];

        imageRobot = new JLabel(new ImageIcon( "D:\\UQAC\\Intelligence artificielle\\devoir1\\robot.png"));
        imageRobot.setBounds(0,0,80,80);
        getContentPane().add(imageRobot);

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                imagesPieces[i][j] = new JLabel( new ImageIcon( "D:\\UQAC\\Intelligence artificielle\\devoir1\\piece.png"));
                imagesPieces[i][j].setBounds(80*i,80*j,80,80);
                getContentPane().add(imagesPieces[i][j]);
            }
        }

//        updatePiece(5,8, true, false);
//        updatePiece(8,2, true, true);
//        updatePiece(1,4, false, true);
//        updatePositionRobot(8,2);
    }

    public void updatePiece(int i, int j, Boolean hasDirt, Boolean hasJewel) {

        getContentPane().remove(imagesPieces[i][j]);

        if(hasDirt && !hasJewel) {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "D:\\UQAC\\Intelligence artificielle\\devoir1\\piece-dirt.png"));
        } else if(hasJewel && !hasDirt) {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "D:\\UQAC\\Intelligence artificielle\\devoir1\\piece-jewel.png"));
        } else if(hasDirt && hasJewel) {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "D:\\UQAC\\Intelligence artificielle\\devoir1\\piece-jeweldirt.png"));
        } else {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "D:\\UQAC\\Intelligence artificielle\\devoir1\\piece.png"));
        }
        imagesPieces[i][j].setBounds(80*i,80*j,80,80);
        getContentPane().add(imagesPieces[i][j]);
        revalidate();
        repaint();
    }

    public void updatePositionRobot(int i, int j) {

        JLabel imagePieceTmp = imagesPieces[i][j];
        getContentPane().remove(imagesPieces[i][j]);

        getContentPane().remove(imageRobot);
        imageRobot.setBounds(80*i,80*j,80,80);
        getContentPane().add(imageRobot);

        imagesPieces[i][j] = imagePieceTmp;
        getContentPane().add(imagesPieces[i][j]);
        revalidate();
        repaint();

    }

}
