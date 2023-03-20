import java.sql.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author yuriy
 */
public class GameFrame extends javax.swing.JFrame {

    /**
     * Creates new form GameFrame
     */
    ArrayList<Question> questions = new ArrayList<Question>();
    private Random  rnd = new Random();
    int Level =0;
    Question currentQuestion;
    
    int Chints = 0;//количество подсказок
    boolean OneMistake = false;//шанс ошибки
    int MySetedReward;//планка цены 
    //boolean ok=false;
    
    
    //метод чтения Файла
    
    public GameFrame() {
        initComponents();
        //ReadFile();
        ReadBD();
        startGame();
    }
    private void ReadFile()
    {
        try{
            FileInputStream fstream = new FileInputStream("C:\\Users\\yuriy\\Downloads\\Компонентное программирование\\лаб6\\WhoWantsToBeAMillionaire\\Вопросы.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                String[] s = strLine.split("\t");
                questions.add(new Question(s));
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
    
    //подключение к бд и считывание данных с нее
    private void ReadBD()
    {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/yuriy/lab6komponentdatabase/question.db");

            //проверка подключения
            /*if (!conn.isClosed()) {
                JOptionPane.showMessageDialog(null, "Connected");
            }
            else {
                JOptionPane.showMessageDialog(null, "Close");
            }*/
            
            Statement statmt = conn.createStatement();
            String query = "select * from questions1";

            ResultSet resSet = statmt.executeQuery(query);


            while (resSet.next()) {
                String[] s = new String[]{resSet.getString(1), resSet.getString(2), resSet.getString(3), resSet.getString(4), resSet.getString(5), resSet.getString(6), resSet.getString(7)};
                questions.add(new Question(s));        
            }
            resSet.close();
            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }
    
    
    //метод отображения вопроса и ответов
    private void ShowQuestion(Question q)
    {     
        lblQuestionText.setText(q.Text);
        btnAnswer1.setText(q.Answers[0]);
        btnAnswer2.setText(q.Answers[1]);
        btnAnswer3.setText(q.Answers[2]);
        btnAnswer4.setText(q.Answers[3]);
    }
    
    //метод получения вопроса с заданным уровнем сложности
    private Question GetQuestion(int level)
    {
        List<Question> list;
        list = questions.stream().filter(q->q.Level==level).collect(Collectors.toList());
        return list.get(rnd.nextInt(list.size()));
    }
    
    //следующий шаг при верном ответе
    private void NextStep()
    {        
        JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4};
        
        for(JButton btn: btns)
            btn.setEnabled(true);
        
        if (Level<15)//после завершения начинается заново
        {
            Level++;
            currentQuestion = GetQuestion(Level);
            ShowQuestion(currentQuestion);        
            lstLevel.setSelectedIndex(lstLevel.getModel().getSize()-Level);   
        }
        else {
            JOptionPane.showMessageDialog(this, "Вы выиграли игру!\nВаш выигрыш 3 000 000 рублей!\nМолодец!");
            startGame();
        }
    }
    
    //начало игры
    private void startGame()
    {
        JOptionPane.showMessageDialog(this, "Выберите несгораемую сумму");
        
        cmbBoxSum.setEnabled(true);
        btnOk.setEnabled(true);
        EnabledAllFields(false); 
        Level = 0;
        Chints=0;
        //NextStep();
    }

    //блокировка/разблокировка всех полей
    private void EnabledAllFields(boolean f)
    {
        btn5050.setEnabled(f);
        ZalHelp.setEnabled(f);
        FriendCall.setEnabled(f);
        btnTheRightToMakeMistakes.setEnabled(f);
        btnChangeQuestion.setEnabled(f);
        btnAnswer1.setEnabled(f);
        btnAnswer2.setEnabled(f);
        btnAnswer3.setEnabled(f);
        btnAnswer4.setEnabled(f);
        TakeDaMoney.setEnabled(f);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn5050 = new javax.swing.JButton();
        ZalHelp = new javax.swing.JButton();
        FriendCall = new javax.swing.JButton();
        TakeDaMoney = new javax.swing.JButton();
        btnAnswer1 = new javax.swing.JButton();
        btnAnswer2 = new javax.swing.JButton();
        btnAnswer4 = new javax.swing.JButton();
        btnAnswer3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstLevel = new javax.swing.JList<>();
        lblQuestionText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnTheRightToMakeMistakes = new javax.swing.JButton();
        btnChangeQuestion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        cmbBoxSum = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn5050.setText("50/50");
        btn5050.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5050ActionPerformed(evt);
            }
        });

        ZalHelp.setText("Помощь зала");
        ZalHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZalHelpActionPerformed(evt);
            }
        });

        FriendCall.setText("Звонок другу");
        FriendCall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FriendCallActionPerformed(evt);
            }
        });

        TakeDaMoney.setText("Забрать деньги");
        TakeDaMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TakeDaMoneyActionPerformed(evt);
            }
        });

        btnAnswer1.setText("btnAnswer1");
        btnAnswer1.setActionCommand("1");
        btnAnswer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer1ActionPerformed(evt);
            }
        });

        btnAnswer2.setText("btnAnswer2");
        btnAnswer2.setActionCommand("2");
        btnAnswer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer2ActionPerformed(evt);
            }
        });

        btnAnswer4.setText("btnAnswer4");
        btnAnswer4.setActionCommand("4");
        btnAnswer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer4ActionPerformed(evt);
            }
        });

        btnAnswer3.setText("btnAnswer3");
        btnAnswer3.setActionCommand("3");
        btnAnswer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnswer3ActionPerformed(evt);
            }
        });

        lstLevel.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "3 000 000", "1 500 000", "   800 000", "   400 000", "   200 000", "   100 000", "     50 000", "     25 000", "     15 000", "     10 000", "       5 000", "       3 000", "       2 000", "       1 000", "          500" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstLevel);

        lblQuestionText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblQuestionText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuestionText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblQuestionText.setName("lblQuestionText"); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/picture.jpg"))); // NOI18N

        btnTheRightToMakeMistakes.setText("Право на ошибку");
        btnTheRightToMakeMistakes.setName("btnTheRightToMakeMistakes"); // NOI18N
        btnTheRightToMakeMistakes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTheRightToMakeMistakesActionPerformed(evt);
            }
        });

        btnChangeQuestion.setText("Замена вопроса");
        btnChangeQuestion.setName("btnChangeQuestion"); // NOI18N
        btnChangeQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeQuestionActionPerformed(evt);
            }
        });

        jLabel2.setText("Несгораемая сумма");

        btnOk.setText("Ок");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        cmbBoxSum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 000 000", "1 500 000", "   800 000", "   400 000", "   200 000", "   100 000", "     50 000", "     25 000", "     15 000", "     10 000", "       5 000", "       3 000", "       2 000", "       1 000", "          500" }));
        cmbBoxSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxSumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAnswer1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addComponent(btnAnswer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnswer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnswer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ZalHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FriendCall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTheRightToMakeMistakes, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(btnChangeQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn5050, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TakeDaMoney, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbBoxSum, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblQuestionText, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn5050, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ZalHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FriendCall, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTheRightToMakeMistakes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangeQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(TakeDaMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBoxSum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk))
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(lblQuestionText, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAnswer3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnswer4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAnswer1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnswer2)))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void btnAnswer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer2ActionPerformed
        bntAnswerPerformed(evt);

    }//GEN-LAST:event_btnAnswer2ActionPerformed

    private void btnAnswer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer4ActionPerformed
        bntAnswerPerformed(evt);

    }//GEN-LAST:event_btnAnswer4ActionPerformed

    private void btn5050ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5050ActionPerformed
        btnFiftyFiftyActionPerformed(evt);
    }//GEN-LAST:event_btn5050ActionPerformed

    private void btnAnswer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer1ActionPerformed
        bntAnswerPerformed(evt);

    }//GEN-LAST:event_btnAnswer1ActionPerformed

    private void btnAnswer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswer3ActionPerformed
        bntAnswerPerformed(evt);
    }//GEN-LAST:event_btnAnswer3ActionPerformed

    
    //блокировка/разблокировка кнопок подсказок
    private void EnabledAllHints(boolean f)
    {
        
        //если использованы уже 4 подсказки
        if (Chints == 4)
        {
            btn5050.setEnabled(f);
            ZalHelp.setEnabled(f);
            FriendCall.setEnabled(f);
            btnTheRightToMakeMistakes.setEnabled(f);
            btnChangeQuestion.setEnabled(f);
        }
        //разблокировка кнопок при новой игре
        else if (f)
        {
            btn5050.setEnabled(f);
            ZalHelp.setEnabled(f);
            FriendCall.setEnabled(f);
            btnTheRightToMakeMistakes.setEnabled(f);
            btnChangeQuestion.setEnabled(f);
        }
    }
    
    
    
    private void btnTheRightToMakeMistakesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTheRightToMakeMistakesActionPerformed

        OneMistake = true;
        Chints++;
        EnabledAllHints(false);
        btnTheRightToMakeMistakes.setEnabled(false);
    }//GEN-LAST:event_btnTheRightToMakeMistakesActionPerformed

    //изменить вопрос
    private void btnChangeQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeQuestionActionPerformed

        currentQuestion = GetQuestion(Level);
        ShowQuestion(currentQuestion);
        lstLevel.setSelectedIndex(lstLevel.getModel().getSize()-Level);
        Chints++;
        EnabledAllHints(false);
        btnChangeQuestion.setEnabled(false);
    }//GEN-LAST:event_btnChangeQuestionActionPerformed

    
    //получение начальной договорной суммы
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed

        MySetedReward = Integer.parseInt(cmbBoxSum.getSelectedItem().toString().replaceAll(" ", ""));
        cmbBoxSum.setEnabled(false);
        btnOk.setEnabled(false);
        lstLevel.setEnabled(false);

        EnabledAllFields(true);
        NextStep();
    }//GEN-LAST:event_btnOkActionPerformed

    
    
    
    
    private void cmbBoxSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxSumActionPerformed
        
        
        
    }//GEN-LAST:event_cmbBoxSumActionPerformed
//помощь зала
    private void ZalHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZalHelpActionPerformed
        //процент голосов на ответы
        double[] answers = new double[]{0.,0.,0.,0.};
        int right = 0;
        double sum = 0;
        JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4};
        
        
        for (int i=0; i<4; i++)
        {
            String ac = btns[i].getActionCommand();
            
            //на правильный ответ процент голосов от 20 до 70
            if (ac.equals(currentQuestion.RightAnswer))
            {
                answers[i] = 20.0 + (double)(Math.random()*50.0);
                sum += answers[i];
                right = i;
                break;
            }
        }
        //на все остальные ответы распределяем оставшиеся проценты голосов
        for (int i=0; i<4; i++)
        {
            if(i!=right){
                answers[i] = 0.0 + (double)(Math.random()*(100-sum));
                sum+=answers[i];
            }
            
        } 
        
        JOptionPane.showMessageDialog(this, btns[0].getText()+": " + String.format("%.2f", answers[0]) + "\n"+btns[1].getText()+": "+": " + String.format("%.2f", answers[1]) + "\n"+btns[2].getText()+": " + String.format("%.2f", answers[2]) + "\n"+btns[3].getText()+": " + String.format("%.2f", answers[3]), "Зал:", 1);
        Chints++;
        EnabledAllHints(false);
        ZalHelp.setEnabled(false);
        
        
    }//GEN-LAST:event_ZalHelpActionPerformed
//звонок другу
    private void FriendCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FriendCallActionPerformed
        //процент голосов на ответы
        double[] answers = new double[]{0.,0.,0.,0.};
        int right = 0;
        double sum = 0;
        JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4};
        
        
        for (int i=0; i<4; i++)
        {
            String ac = btns[i].getActionCommand();
            
            //на правильный ответ процент голосов от 30 до 80
            if (ac.equals(currentQuestion.RightAnswer))
            {
                answers[i] = 20.0 + (double)(Math.random()*50.0);
                sum += answers[i];
                right = i;
                break;
            }
        }
        //на все остальные ответы распределяем оставшиеся проценты голосов
        for (int i=0; i<4; i++)
        {
            if(i!=right){
                answers[i] = 0.0 + (double)(Math.random()*(100-sum));
                sum+=answers[i];
            }            
        } 
        int s=0;
        for(int i=1;i<4;i++){
            if(answers[i]>answers[s]){
                s=i;
            }
        }
        
        JOptionPane.showMessageDialog(this, "Я думаю правильный ответ " + btns[s].getText(), "Друг:", 1);
        Chints++;
        EnabledAllHints(false);
        FriendCall.setEnabled(false);       
        
    }//GEN-LAST:event_FriendCallActionPerformed
//хочу взять деньги
    private void TakeDaMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TakeDaMoneyActionPerformed

        if (Level > 15-getMySetedRreward())
        {
            JOptionPane.showMessageDialog(this, "Вы забрали деньги, ваш выигрыш составил " + lstLevel.getModel().getElementAt(getMySetedRreward()) + " рублей!!!");
            startGame();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Вы не можете забрать деньги!");
        }
    }//GEN-LAST:event_TakeDaMoneyActionPerformed
//получение индекса начальной суммы
    private int getMySetedRreward(){
        for (int i=0; i<lstLevel.getModel().getSize(); i++)
        {
            String s = lstLevel.getModel().getElementAt(i).replaceAll(" ", "");
            if (MySetedReward == Integer.parseInt(s))
            {                
                return i;
            }
        }  
        return 0;
    }
    
    
    //произведен выбор ответа
    private void bntAnswerPerformed(java.awt.event.ActionEvent evt) {                                    

        JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4};
        if (currentQuestion.RightAnswer.equals(evt.getActionCommand()))
            NextStep();            
        else if(OneMistake==false)
        {
            JOptionPane.showMessageDialog(this, "Неверный ответ!" + '\n'+"Правильный ответ: \n"+btns[Integer.parseInt(currentQuestion.RightAnswer)-1].getText());
            startGame();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Неверный ответ!" + '\n'+"использован шанс на неверный ответ");
        }
    }
    
//подсказка "50/50"
    private void btnFiftyFiftyActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
       JButton[] btns = new JButton[]{btnAnswer1, btnAnswer2, 
            btnAnswer3, btnAnswer4};
        
        int count = 0;
        while (count<2)
        {
            int n = rnd.nextInt(4);
            String ac = btns[n].getActionCommand();
            
            if (!ac.equals(currentQuestion.RightAnswer)
                    && btns[n].isEnabled())
            {
                btns[n].setEnabled(false);
                count++;
            }
        }
        Chints++;
        EnabledAllHints(false);
        btn5050.setEnabled(false);
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FriendCall;
    private javax.swing.JButton TakeDaMoney;
    private javax.swing.JButton ZalHelp;
    private javax.swing.JButton btn5050;
    private javax.swing.JButton btnAnswer1;
    private javax.swing.JButton btnAnswer2;
    private javax.swing.JButton btnAnswer3;
    private javax.swing.JButton btnAnswer4;
    private javax.swing.JButton btnChangeQuestion;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnTheRightToMakeMistakes;
    private javax.swing.JComboBox<String> cmbBoxSum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblQuestionText;
    private javax.swing.JList<String> lstLevel;
    // End of variables declaration//GEN-END:variables
}
