package ui.component;

import ui.model.MockData;
import ui.model.ResultModel;
import ui.model.Wrapper;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

public class ProgressPanel implements PropertyChangeListener {
    private ProgressMonitor progressMonitor;
    private Task task;
    private JButton button;
    private JList result;
    private String searchTerm;

    class Task extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            try {
                Thread.sleep(1000);
                while (progress < 100 && !isCancelled()) {
                    //Sleep for up to one second.
                    Thread.sleep(random.nextInt(1000));
                    //Make random progress.
                    progress += random.nextInt(10);
                    setProgress(Math.min(progress, 100));
                }
            } catch (InterruptedException ignore) {}
            return null;
        }
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            button.setEnabled(true);
            progressMonitor.setProgress(0);
            progressMonitor.close();
            MockData source = new MockData();
            Wrapper[] lists;
            ResultModel model = new ResultModel();
            lists = source.getData(searchTerm);
            model.updateModel(source.buildString(lists));
            result.setModel(model);
        }
    }

    public ProgressPanel(Component parent, String message, JButton button, JList list, String searchTerm) {
        progressMonitor = new ProgressMonitor(parent, message, "", 0, 100);
        progressMonitor.setProgress(0);
        task = new Task();
        task.addPropertyChangeListener(this);
        this.button = button;
        this.result = list;
        this.searchTerm = searchTerm;
    }

    public void activeProgress(){
        task.execute();
        button.setEnabled(false);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName() ) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            String message =
                    String.format("Completed %d%%.\n", progress);
            progressMonitor.setNote(message);
//            taskOutput.append(message);
            if (progressMonitor.isCanceled() || task.isDone()) {
                Toolkit.getDefaultToolkit().beep();
//                if (progressMonitor.isCanceled()) {
//                    task.cancel(true);
//                    taskOutput.append("Task canceled.\n");
//                } else {
//                    taskOutput.append("Task completed.\n");
//                }
//                startButton.setEnabled(true);
            }
        }
    }

}
