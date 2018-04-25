package ui;

import org.jfree.data.category.CategoryDataset;
import ui.component.ProgressPanel;
import ui.controller.ImageParser;
import ui.controller.PlaySound;
import ui.controller.VideoPlayer;

import ui.component.LineChart;
import ui.model.AllResultModel;
import ui.model.ResultModel;
import videosearch.VideoSearch;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class VideoSearchUI extends JFrame {
    public VideoSearchUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        desciptorGroup = new javax.swing.ButtonGroup();
        SearchPanel = new javax.swing.JPanel();
        searchTag = new javax.swing.JLabel();
        queryTerm = new javax.swing.JTextField();
        submission = new javax.swing.JButton();
        loadedsource = new javax.swing.JButton();
        ResultPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        result = new javax.swing.JList<>();
        resultTag = new javax.swing.JLabel();
        loadedResult = new javax.swing.JButton();
        TypePanel = new javax.swing.JPanel();
        motion = new javax.swing.JRadioButton();
        color = new javax.swing.JRadioButton();
        baseFreq = new javax.swing.JRadioButton();
        visualDspLabel = new javax.swing.JLabel();
        soundDspLabel = new javax.swing.JLabel();
        dominantFreq = new javax.swing.JRadioButton();
        soundPressLevel = new javax.swing.JRadioButton();
        rootMeanSquare = new javax.swing.JRadioButton();
        SourcePreview = new javax.swing.JPanel();
        playS = new javax.swing.JButton();
        pauseS = new javax.swing.JButton();
        stopS = new javax.swing.JButton();
        queryImageLabel = new javax.swing.JLabel();
        queryPreviewLabel = new javax.swing.JLabel();
        ResultPreview = new javax.swing.JPanel();
        playR = new javax.swing.JButton();
        pauseR = new javax.swing.JButton();
        stopR = new javax.swing.JButton();
        dbImageLabel = new javax.swing.JLabel();
        dbPreviewLabel = new javax.swing.JLabel();
        VisualResult = new javax.swing.JPanel();
        frameChoose = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchTag.setText("Find:");

        submission.setText("search");

        loadedsource.setText("Load Query Video");


        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
                SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(searchTag, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(queryTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loadedsource)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(submission)
                                .addContainerGap())
        );
        SearchPanelLayout.setVerticalGroup(
                SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchTag)
                                        .addComponent(queryTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(submission)
                                        .addComponent(loadedsource))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        result.setModel( new ResultModel()
//                new javax.swing.AbstractListModel<String>() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public String getElementAt(int i) { return strings[i]; }
//        }
        );
        jScrollPane1.setViewportView(result);

        resultTag.setText("Result");

        loadedResult.setText("Load Result Video");


        javax.swing.GroupLayout ResultPanelLayout = new javax.swing.GroupLayout(ResultPanel);
        ResultPanel.setLayout(ResultPanelLayout);
        ResultPanelLayout.setHorizontalGroup(
                ResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ResultPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(resultTag)
                                .addGap(18, 18, 18)
                                .addGroup(ResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loadedResult, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        ResultPanelLayout.setVerticalGroup(
                ResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ResultPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(resultTag)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(ResultPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loadedResult)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        desciptorGroup.add(motion);
        motion.setText("Motion");

        desciptorGroup.add(color);
        color.setText("Color");

        desciptorGroup.add(baseFreq);
        baseFreq.setText("Base frequency");

        visualDspLabel.setText("Visual Discriptor");

        soundDspLabel.setText("Sound Discriptor");

        desciptorGroup.add(dominantFreq);
        dominantFreq.setText("Dominant frequency");

        desciptorGroup.add(soundPressLevel);
        soundPressLevel.setText("Sound pressure level");

        desciptorGroup.add(rootMeanSquare);
        rootMeanSquare.setText("Root mean square");

        javax.swing.GroupLayout TypePanelLayout = new javax.swing.GroupLayout(TypePanel);
        TypePanel.setLayout(TypePanelLayout);
        TypePanelLayout.setHorizontalGroup(
                TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TypePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(color)
                                        .addComponent(motion)
                                        .addComponent(visualDspLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rootMeanSquare)
                                        .addComponent(soundPressLevel)
                                        .addComponent(baseFreq)
                                        .addComponent(soundDspLabel)
                                        .addComponent(dominantFreq))
                                .addGap(21, 21, 21))
        );

        TypePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {baseFreq, color, motion});

        TypePanelLayout.setVerticalGroup(
                TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(TypePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(visualDspLabel)
                                        .addComponent(soundDspLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(motion)
                                        .addComponent(baseFreq))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(TypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(color)
                                        .addComponent(dominantFreq))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soundPressLevel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rootMeanSquare)
                                .addContainerGap())
        );

        playS.setText("Play");

        pauseS.setText("Pause");

        stopS.setText("Stop");

        queryImageLabel.setIcon(new ImageIcon(defaultImg));

        queryPreviewLabel.setText("Query Video Preview");

        javax.swing.GroupLayout SourcePreviewLayout = new javax.swing.GroupLayout(SourcePreview);
        SourcePreview.setLayout(SourcePreviewLayout);
        SourcePreviewLayout.setHorizontalGroup(
                SourcePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SourcePreviewLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pauseS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopS)
                                .addGap(48, 48, 48))
                        .addGroup(SourcePreviewLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(SourcePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(queryImageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                        .addGroup(SourcePreviewLayout.createSequentialGroup()
                                                .addComponent(queryPreviewLabel)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        SourcePreviewLayout.setVerticalGroup(
                SourcePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SourcePreviewLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(queryPreviewLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(queryImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(SourcePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(playS)
                                        .addComponent(pauseS)
                                        .addComponent(stopS))
                                .addContainerGap())
        );

        playR.setText("Play");

        pauseR.setText("Pause");

        stopR.setText("Stop");

        dbImageLabel.setIcon(new ImageIcon(defaultImg));

        dbPreviewLabel.setText("DataBase Video Preview");

        javax.swing.GroupLayout ResultPreviewLayout = new javax.swing.GroupLayout(ResultPreview);
        ResultPreview.setLayout(ResultPreviewLayout);
        ResultPreviewLayout.setHorizontalGroup(
                ResultPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResultPreviewLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pauseR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopR)
                                .addGap(48, 48, 48))
                        .addGroup(ResultPreviewLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(ResultPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dbImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                        .addGroup(ResultPreviewLayout.createSequentialGroup()
                                                .addComponent(dbPreviewLabel)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        ResultPreviewLayout.setVerticalGroup(
                ResultPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ResultPreviewLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(dbPreviewLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dbImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(ResultPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(stopR)
                                        .addComponent(playR)
                                        .addComponent(pauseR))
                                .addContainerGap())
        );

        frameChoose.setMaximum(150);
        frameChoose.setMinimum(1);
        frameChoose.setValue(1);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(ResultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(frameChoose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(VisualResult, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(SourcePreview, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ResultPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ResultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(VisualResult, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(frameChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(SourcePreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ResultPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();

        // add manually
        motion.setActionCommand("rgb image motion");
        color.setActionCommand("rgb color");
        baseFreq.setActionCommand("base frequency");
        dominantFreq.setActionCommand("dominant frequency");
        soundPressLevel.setActionCommand("sound pressure level");
        rootMeanSquare.setActionCommand("root mean square");


        result.addListSelectionListener(e -> selectedResultPerformed(e));

        playS.addActionListener(e -> playActionPerformed(e));
        stopS.addActionListener(e -> stopActionPerformed(e));
        pauseS.addActionListener(e -> pauseActionPerformed(e));
        playR.addActionListener(e -> playActionPerformed(e));
        stopR.addActionListener(e -> stopActionPerformed(e));
        pauseR.addActionListener(e -> pauseActionPerformed(e));


        submission.addActionListener(e -> queryTermActionPerformed(e));
        loadedsource.addActionListener(e -> loadedSourceActionPerformed(e));
        loadedResult.addActionListener(e -> loadedResultActionPerformed(e));
        frameChoose.addChangeListener(e -> frameChooseStateChanged(e));
        motion.addActionListener(e -> descriptorSelectedActionPerformed(e));
        color.addActionListener(e -> descriptorSelectedActionPerformed(e));
        baseFreq.addActionListener(e -> descriptorSelectedActionPerformed(e));
        dominantFreq.addActionListener(e -> descriptorSelectedActionPerformed(e));
        soundPressLevel.addActionListener(e -> descriptorSelectedActionPerformed(e));
        rootMeanSquare.addActionListener(e -> descriptorSelectedActionPerformed(e));


    }// </editor-fold>

    /*
     * When the user submit the query term, do the action
     */
    private void queryTermActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String query = this.queryTerm.getText().trim();
        ButtonModel choice = desciptorGroup.getSelection();
        if(checkInput(query)) {
//            ProgressPanel monitor = new ProgressPanel(VideoSearchUI.this, "Compare with Database...",
//                    this.submission, this.result, query);
//            monitor.activeProgress(searcher);
//            String method = choice.getActionCommand();
//            System.out.println(method);
            searcher.loadQuery(query);
            searcher.compareDB();

            String method;
            if(choice == null) {
                method = DEFAULT_METHOD;
                baseFreq.setSelected(true);
            }else {
                method = choice.getActionCommand();
            }
            ResultModel model = new ResultModel();
            String[] resultList = searcher.getStoredResult().getResultList(method);
            model.updateModel(resultList);
            result.setModel(model);
//            lists = source.getData(query);
//            model.updateModel(source.buildString(lists));
//            this.result.setModel(model);
        }

    }

    private void descriptorSelectedActionPerformed(ActionEvent evt) {
        String method = desciptorGroup.getSelection().getActionCommand();
        this.VisualResult.removeAll();
        this.VisualResult.revalidate();

        if(!searcher.getStoredResult().isResultEmpty()) {
            ResultModel model = new ResultModel();
            String[] resultList = searcher.getStoredResult().getResultList(method);
            model.updateModel(resultList);
            result.setModel(model);
        }
        System.out.println(method);
    }


    private boolean checkInput(String query) {
        if(query != null && !query.isEmpty())
            return true;

        if(query == null) {
            JOptionPane.showMessageDialog(null,
                    "query cannot be empty",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else if(query.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "query cannot be empty",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /*
     *  Handle when the slide bar is changed
     */
    private void frameChooseStateChanged(javax.swing.event.ChangeEvent evt) {
        JSlider source = (JSlider) evt.getSource();
        if(!source.getValueIsAdjusting()) {
            System.out.println("stop adjusted");
            int frameNum = source.getValue();
            System.out.println("Move to frame Num: " + frameNum);
            this.queryControl.setCurrentFrame(frameNum);
            this.queryControl.displayDefaultImg();
            this.dbControl.setCurrentFrame(frameNum);
            this.dbControl.displayDefaultImg();
        }
    }

    private void selectedResultPerformed(ListSelectionEvent evt) {
        if(!evt.getValueIsAdjusting()) {
            LineChart lineChart = new LineChart(this.VisualResult);
            String method = this.desciptorGroup.getSelection().getActionCommand();
            String videoName = result.getSelectedValue();
//            System.out.println(result.getSelectedValue());
            CategoryDataset dataSets = this.searcher.getStoredResult().getDistribution(method, videoName);
            lineChart.update(dataSets);
        }
    }

    private void playActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.playS) {
            queryControl.play();
        }else if(evt.getSource() == this.playR) {
            // TODO Result Preview player
            dbControl.play();
        }

    }

    private void pauseActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.pauseS) {
            queryControl.pause();
        }else if(evt.getSource() == this.pauseR) {
            // TODO Result Preview player
            dbControl.pause();
        }
    }

    private void stopActionPerformed(ActionEvent evt) {
        if(evt.getSource() == this.stopS) {
            queryControl.stop();
        }else if(evt.getSource() == this.stopR) {
            dbControl.stop();
        }
    }

    private void loadedSourceActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO bind query term:
        String query = this.queryTerm.getText().trim();
        if(query == null || query.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "query is empty",
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        ImageParser source= new ImageParser(150, query, QUERY_FOLDER);
        queryFrames = source.parseAllImg(FRAME_ON_QUERY, true);
        queryImageLabel.setIcon(new ImageIcon(queryFrames[FRAME_ON_QUERY]));

        String audioPath = QUERY_FOLDER + query + "/" + query + ".wav";
        PlaySound soundPlayer = new PlaySound(audioPath);
        this.queryControl = new VideoPlayer(150,queryImageLabel,queryFrames, soundPlayer);

    }

    private void loadedResultActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(result.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null,
                    "Please select one result",
                    "Load Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String method = this.desciptorGroup.getSelection().getActionCommand();
        String videoName = result.getSelectedValue();
        int startFrame = searcher.getStoredResult().getResultStartAt(method,videoName);
        ImageParser source = new ImageParser(150, videoName, DB_FOLDER);
        resultFrames = source.parseAllImg(startFrame, false);
        dbImageLabel.setIcon(new ImageIcon(resultFrames[FRAME_ON_QUERY]));

        String audioPath = DB_FOLDER + videoName + "/" +videoName + ".wav";
        PlaySound soundPlayer = new PlaySound(audioPath);
        this.dbControl = new VideoPlayer(150, dbImageLabel, resultFrames, soundPlayer);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VideoSearchUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CelsiusConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CelsiusConverterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
//        VideoSearchUI ui = new VideoSearchUI();
//        ui.setVisible(true);
        searcher.loadDataBase();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VideoSearchUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel ResultPanel;
    private javax.swing.JPanel ResultPreview;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JPanel SourcePreview;
    private javax.swing.JPanel TypePanel;
    private javax.swing.JPanel VisualResult;
    private javax.swing.JPanel VisualResultPanel;
    private javax.swing.JRadioButton baseFreq;
    private javax.swing.JRadioButton color;
    private javax.swing.JLabel dbImageLabel;
    private javax.swing.JLabel dbPreviewLabel;
    private javax.swing.ButtonGroup desciptorGroup;
    private javax.swing.JRadioButton dominantFreq;
    private javax.swing.JSlider frameChoose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadedResult;
    private javax.swing.JButton loadedsource;
    private javax.swing.JRadioButton motion;
    private javax.swing.JButton pauseR;
    private javax.swing.JButton pauseS;
    private javax.swing.JButton playR;
    private javax.swing.JButton playS;
    private javax.swing.JLabel queryImageLabel;
    private javax.swing.JLabel queryPreviewLabel;
    private javax.swing.JTextField queryTerm;
    private javax.swing.JList<String> result;
    private javax.swing.JLabel resultTag;
    private javax.swing.JRadioButton rootMeanSquare;
    private javax.swing.JLabel searchTag;
    private javax.swing.JLabel soundDspLabel;
    private javax.swing.JRadioButton soundPressLevel;
    private javax.swing.JButton stopR;
    private javax.swing.JButton stopS;
    private javax.swing.JButton submission;
    private javax.swing.JLabel visualDspLabel;
    // End of variables declaration

    // custom variable for handling event
    private String defaultImg = "assets/default.png";
    private BufferedImage[] queryFrames;
    private BufferedImage[] resultFrames;
    private VideoPlayer queryControl;
    private VideoPlayer dbControl;
    private static VideoSearch searcher = new VideoSearch();
    private final static String DEFAULT_METHOD = "base frequency";
    private final static String QUERY_FOLDER = "query/";
    private final static String DB_FOLDER = "database_videos/";
    private final static int FRAME_ON_QUERY = 1;
}
