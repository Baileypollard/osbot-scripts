/*
 * Created by JFormDesigner on Sun Oct 06 20:36:42 ADT 2019
 */

package Castle_Wars.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

import Castle_Wars.constants.Team;

/**
 * @author bailey pollard
 */
public class CastleWarsGUI extends JFrame {
    public CastleWarsGUI() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - bailey pollard
        this2 = new JDialog();
        label1 = new JLabel();
        label2 = new JLabel();
        guthixPortalButton = new JRadioButton();
        zammyPortalButton = new JRadioButton();
        saraPortalButton = new JRadioButton();
        alternateTeamsButton = new JRadioButton();
        label3 = new JLabel();
        afkButton = new JRadioButton();
        protectFlagButton = new JRadioButton();
        startButton = new JButton();

        //======== this2 ========
        {
            this2.setVisible(true);
            Container this2ContentPane = this2.getContentPane();

            //---- label1 ----
            label1.setText("RatsCastleWars");
            label1.setHorizontalAlignment(SwingConstants.CENTER);

            //---- label2 ----
            label2.setText("Join the");
            label2.setAlignmentX(20.0F);
            label2.setHorizontalAlignment(SwingConstants.LEFT);
            label2.setBorder(new EmptyBorder(5, 5, 5, 5));

            //---- guthixPortalButton ----
            guthixPortalButton.setText("Guthix Portal");
            guthixPortalButton.setSelected(true);

            //---- zammyPortalButton ----
            zammyPortalButton.setText("Zamorak Portal");

            //---- saraPortalButton ----
            saraPortalButton.setText("Saradomin Portal");

            //---- alternateTeamsButton ----
            alternateTeamsButton.setText("Alternate Portals");

            //---- label3 ----
            label3.setText("Playing method");
            label3.setBorder(new EmptyBorder(5, 5, 5, 5));

            //---- afkButton ----
            afkButton.setText("AFK");
            afkButton.setSelected(true);

            //---- protectFlagButton ----
            protectFlagButton.setText("Protect Flag");

            //---- startButton ----
            startButton.setText("Start");
            startButton.setBorder(new EmptyBorder(5, 5, 5, 5));

            GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
            this2ContentPane.setLayout(this2ContentPaneLayout);
            this2ContentPaneLayout.setHorizontalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addGroup(this2ContentPaneLayout.createParallelGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(guthixPortalButton, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(zammyPortalButton, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(saraPortalButton, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(alternateTeamsButton, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(afkButton, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(protectFlagButton, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                            .addComponent(startButton, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            this2ContentPaneLayout.setVerticalGroup(
                this2ContentPaneLayout.createParallelGroup()
                    .addGroup(this2ContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2)
                        .addGap(0, 0, 0)
                        .addComponent(guthixPortalButton)
                        .addGap(0, 0, 0)
                        .addComponent(zammyPortalButton)
                        .addGap(0, 0, 0)
                        .addComponent(saraPortalButton)
                        .addGap(0, 0, 0)
                        .addComponent(alternateTeamsButton)
                        .addGap(0, 0, 0)
                        .addComponent(label3)
                        .addGap(0, 0, 0)
                        .addComponent(afkButton)
                        .addGap(0, 0, 0)
                        .addComponent(protectFlagButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startButton)
                        .addContainerGap())
            );
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }

        //---- portalSelectionGroup ----
        ButtonGroup portalSelectionGroup = new ButtonGroup();
        portalSelectionGroup.add(guthixPortalButton);
        portalSelectionGroup.add(zammyPortalButton);
        portalSelectionGroup.add(saraPortalButton);
        portalSelectionGroup.add(alternateTeamsButton);

        //---- playStyleGroup ----
        ButtonGroup playStyleGroup = new ButtonGroup();
        playStyleGroup.add(afkButton);
        playStyleGroup.add(protectFlagButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:Location
    // Generated using JFormDesigner Evaluation license - bailey pollard
    private JDialog this2;
    private JLabel label1;
    private JLabel label2;
    private JRadioButton guthixPortalButton;
    private JRadioButton zammyPortalButton;
    private JRadioButton saraPortalButton;
    private JRadioButton alternateTeamsButton;
    private JLabel label3;
    private JRadioButton afkButton;
    private JRadioButton protectFlagButton;
    private JButton startButton;
    // JFormDesigner - End of Location declaration  //GEN-END:Location



    public JButton getStartButton() {
        return startButton;
    }

    public JDialog getGUI() {
        return this2;
    }

    public Team getSelectedTeam() {
        if (guthixPortalButton.isSelected())
            return Team.GUTHIX;
        else if (saraPortalButton.isSelected())
            return Team.SARADOMIN;
        else if (zammyPortalButton.isSelected())
            return Team.ZAMORAK;
        else
            return Team.SARADOMIN;
    }
}
