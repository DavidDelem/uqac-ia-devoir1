package com.company.interfacegraphique;

import javax.swing.*;

/**
 Affichage des éléments à l'écran
 */

public class Fenetre extends JFrame {

    private JLabel[][] imagesPieces;
    private JLabel imageRobot;
    private JLabel nbPiecesPropresLabel;
    private JLabel nbPoussieresLabel;
    private JLabel nbJewelsLabel;
    private JLabel nbPointsLabel;

    public Fenetre() {
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(815, 905);
        getContentPane().setLayout(null);
        CreateElements();
        setVisible(true);
    }

    private void CreateElements() {
        imagesPieces = new JLabel[10][10];

        imageRobot = new JLabel(new ImageIcon( "robot.png"));
        imageRobot.setBounds(0,0,80,80);
        getContentPane().add(imageRobot);

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                imagesPieces[i][j] = new JLabel( new ImageIcon( "piece.png"));
                imagesPieces[i][j].setBounds(80*i,80*j,80,80);
                getContentPane().add(imagesPieces[i][j]);
            }
        }

        nbPiecesPropresLabel = new JLabel("Nombre de piéces propres : 100");
        nbPiecesPropresLabel.setBounds(20,790,200,80);
        getContentPane().add(nbPiecesPropresLabel);

        nbPoussieresLabel = new JLabel("Nombre de poussières : 0");
        nbPoussieresLabel.setBounds(230,790,200,80);
        getContentPane().add(nbPoussieresLabel);

        nbJewelsLabel = new JLabel("Nombre de bijoux : 0");
        nbJewelsLabel.setBounds(420,790,200,80);
        getContentPane().add(nbJewelsLabel);

        nbPointsLabel = new JLabel("Nombre de points du robot : 0");
        nbPointsLabel.setBounds(590,790,200,80);
        getContentPane().add(nbPointsLabel);
    }

    public void updatePiece(int i, int j, Boolean hasDirt, Boolean hasJewel) {

        getContentPane().remove(imagesPieces[i][j]);

        if(hasDirt && !hasJewel) {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "piece-dirt.png"));
        } else if(hasJewel && !hasDirt) {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "piece-jewel.png"));
        } else if(hasDirt && hasJewel) {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "piece-jeweldirt.png"));
        } else {
            imagesPieces[i][j] = new JLabel( new ImageIcon( "piece.png"));
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
    public void updateNbPiecesPropresLabel(String texte) {
        nbPiecesPropresLabel.setText("Nombre de pièces propres : " + texte);
    }

    public void updateNbPoussieresLabel(String texte) {
        nbPoussieresLabel.setText("Nombre de poussières : " + texte);
    }

    public void updateNbJewelsLabel(String texte) {
        nbJewelsLabel.setText("Nombre de bijoux : " + texte);
    }

    public void updateNbPoints(String texte) {
        nbPointsLabel.setText("Nombre de points du robot : " + texte);
    }

}
