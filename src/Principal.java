import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/** @author miguelogin **/

public class Principal extends javax.swing.JFrame {

    ImageIcon icon0 = new ImageIcon("src/images/hangman/forca_00.png");
    ImageIcon icon1 = new ImageIcon("src/images/hangman/forca_01.png");
    ImageIcon icon2 = new ImageIcon("src/images/hangman/forca_02.png");
    ImageIcon icon3 = new ImageIcon("src/images/hangman/forca_03.png");
    ImageIcon icon4 = new ImageIcon("src/images/hangman/forca_04.png");
    ImageIcon icon5 = new ImageIcon("src/images/hangman/forca_05.png");
    ImageIcon icon6 = new ImageIcon("src/images/hangman/forca_06.png");
    ImageIcon icon7 = new ImageIcon("src/images/hangman/forca_07.png");
    ImageIcon icon8 = new ImageIcon("src/images/hangman/forca_08.png");
    ImageIcon icon9 = new ImageIcon("src/images/hangman/forca_09.png");

    ImageIcon victory = new ImageIcon("src/images/victory.png");
    ImageIcon defeat = new ImageIcon("src/images/defeat.png");
    
    String palavra_sorteada = new String();
    int palavra_cod, old_palavra_cod;
    ArrayList tentativas = new ArrayList();
    int num_tentativas = 0 ;
    String letra;
    boolean end_game = true;
    ArrayList palavras_temp = new ArrayList();
    ArrayList dica_temp = new ArrayList();
    
    
    int op = 0;
    String temp = new String ("");
    String word_dica = new String ("");
    
    public Principal() {
        initComponents();
        setIcon();
        desativa_botoes();
    }
    
    public void sortear_palavra() {
        try {
            //File cria_arquivo = new File("src//palavras.txt"); 
            //cria_arquivo.createNewFile(); 
            
            File arquivo = new File("src/palavras.txt");
            FileReader leitor = new FileReader(arquivo);
            BufferedReader leitor_bufffer = new BufferedReader(leitor);
            palavras_temp.clear(); //limpa o temporario que armazena as palavras
            dica_temp.clear(); //limpa o temporaio que aramzena a dica

            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            String linha = null;

            while((linha = leitor_bufffer.readLine()) != null){
                String colunas[] = linha.split("#"); //lê a linha contendo a palavra e a dica, sabendo q as mesmas sao separadas pelo #
                palavras_temp.add(colunas[0]);
                dica_temp.add(colunas[1]);
            }

            leitor_bufffer.close();

            int total_palavras = palavras_temp.size();                  //lê o total de palavras
            do{
                palavra_cod = (int) (Math.random()*(total_palavras));   //sorteia uma palavra randomica de zero até o total de palavras
            }while(palavra_cod == old_palavra_cod);
            old_palavra_cod = palavra_cod;
            palavra_sorteada = (String) palavras_temp.get(palavra_cod); //pega a plavra sorteada
            palavra_sorteada = palavra_sorteada.toUpperCase();          //coloca em maiusculo
            dica.setText("DICA: "+(String) dica_temp.get(palavra_cod)); //pega a dica referente a palavra sorteada e exibe
            imprimir_palavra(end_game);                                 //chama a impressão da palavra
            ativa_botoes();
        }
        catch(Exception ex) {
            System.out.println("NÃO OK!");
        }
    }

    public void imprimir_palavra (boolean for_coparacao){
        String texto_temp = new String();
        boolean resta_um = false;
        if(palavra_cod != 0){
            for (int n=1; n<=palavra_sorteada.length(); n++){       //pega o tamanho da palavra para gerar os traços
                if(((for_coparacao) || tentativas.contains(palavra_sorteada.substring(n-1, n))))
                {
                    texto_temp = texto_temp + palavra_sorteada.substring(n-1, n); //revela a letra chutada

                    //if (timer == palavra_sorteada.length())
                    //{
                    //   resta_um = false;
                    //    timer = 0;
                    //}
                }else
                {
                    texto_temp = texto_temp + "_";
                    resta_um = true;
                }
                texto_temp = texto_temp + " ";
            }
        }else{
            for (int n=1; n<=palavra_sorteada.length(); n++){       //pega o tamanho da palavra para gerar os traços
                if(((for_coparacao) || tentativas.contains(palavra_sorteada.substring(n-1, n))))
                {
                    texto_temp = texto_temp + palavra_sorteada.substring(n-1, n); //revela a letra chutada
                }else
                {
                    texto_temp = texto_temp + "_";
                    resta_um = true;
                }
                texto_temp = texto_temp +" ";
            }
        }

        palavra_view.setText(texto_temp);

        if(!resta_um){
            end_game = true;
            if ((num_tentativas)<=9){    //verifica se o numero de tentativas é menor que 6
                    forca_image.setIcon(victory);
                    desativa_botoes();
            }
        }
    }
    
    public void confere (){
        if (!end_game)
        {
            if(tentativas.contains(letra)){
            }else{                                      
            tentativas.add(letra);  // Armazena a letra nova na lista de tentativas.
            if(palavra_sorteada.contains(letra)){
            // Acertou
            imprimir_palavra(false);
            }else{
                num_tentativas = num_tentativas + 1;    //vai adicionando o numero de tentativas; se errar, adiciona o desenho
                switch(num_tentativas){
                    case(1):{
                        forca_image.setIcon(icon1);
                        break;
                    }
                    case(2):{
                        forca_image.setIcon(icon2);
                        break;
                    }
                    case(3):{
                        forca_image.setIcon(icon3);
                        break;
                    }
                    case(4):{
                        forca_image.setIcon(icon4);
                        break;
                    }
                    case(5):{
                        forca_image.setIcon(icon5);
                        break;
                    }
                    case(6):{
                        forca_image.setIcon(icon6);
                        break;
                    }
                    case(7):{
                        forca_image.setIcon(icon7);
                        break;
                    }
                    case(8):{
                        forca_image.setIcon(icon8);
                        break;
                    }
                    case(9):{
                        forca_image.setIcon(icon9);
                        end_game=true;
                        desativa_botoes();
                        imprimir_palavra(true);
                        forca_image.setIcon(defeat);
                        dica.setText("Perdeu, troxão! A palavra era:");
                        end_game=true;
                        break;
                    }
                }
            }
        }
    }
    }
  
    public void max_caracter ()
    {
        if (temp.length() == 30)
        {
            JOptionPane.showMessageDialog(null, "LIMITE DE LETRAS ATINGIDO!", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            temp = temp + letra;
            user_word.setText(temp);
        }
    }
    
    public void desativa_botoes()
    {
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        e.setEnabled(false);
        f.setEnabled(false);
        g.setEnabled(false);
        h.setEnabled(false);
        i.setEnabled(false);
        j.setEnabled(false);
        k.setEnabled(false);
        l.setEnabled(false);
        m.setEnabled(false);
        n.setEnabled(false);
        o.setEnabled(false);
        p.setEnabled(false);
        q.setEnabled(false);
        r.setEnabled(false);
        s.setEnabled(false);
        t.setEnabled(false);
        u.setEnabled(false);
        v.setEnabled(false);
        w.setEnabled(false);
        x.setEnabled(false);
        y.setEnabled(false);
        z.setEnabled(false);
    }
    
    public void ativa_botoes ()
    {
        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);
        e.setEnabled(true);
        f.setEnabled(true);
        g.setEnabled(true);
        h.setEnabled(true);
        i.setEnabled(true);
        j.setEnabled(true);
        k.setEnabled(true);
        l.setEnabled(true);
        m.setEnabled(true);
        n.setEnabled(true);
        o.setEnabled(true);
        p.setEnabled(true);
        q.setEnabled(true);
        r.setEnabled(true);
        s.setEnabled(true);
        t.setEnabled(true);
        u.setEnabled(true);
        v.setEnabled(true);
        w.setEnabled(true);
        x.setEnabled(true);
        y.setEnabled(true);
        z.setEnabled(true);
    }
    
    public void reinicia ()
    {
        palavra_view.setText("");
        tentativas.clear();
        sortear_palavra();
        end_game = false;
        forca_image.setIcon(icon0);
        num_tentativas = 0;
        imprimir_palavra(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adicionar_palavra = new javax.swing.JDialog();
        x1 = new javax.swing.JButton();
        d1 = new javax.swing.JButton();
        y1 = new javax.swing.JButton();
        e1 = new javax.swing.JButton();
        f1 = new javax.swing.JButton();
        z1 = new javax.swing.JButton();
        g1 = new javax.swing.JButton();
        h1 = new javax.swing.JButton();
        i1 = new javax.swing.JButton();
        j1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        t1 = new javax.swing.JButton();
        u1 = new javax.swing.JButton();
        a1 = new javax.swing.JButton();
        v1 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        w1 = new javax.swing.JButton();
        c1 = new javax.swing.JButton();
        k1 = new javax.swing.JButton();
        l1 = new javax.swing.JButton();
        m1 = new javax.swing.JButton();
        n1 = new javax.swing.JButton();
        o1 = new javax.swing.JButton();
        p1 = new javax.swing.JButton();
        q1 = new javax.swing.JButton();
        r1 = new javax.swing.JButton();
        s1 = new javax.swing.JButton();
        user_word = new javax.swing.JTextField();
        forca_image = new javax.swing.JLabel();
        t = new javax.swing.JButton();
        dica = new javax.swing.JLabel();
        u = new javax.swing.JButton();
        a = new javax.swing.JButton();
        v = new javax.swing.JButton();
        b = new javax.swing.JButton();
        w = new javax.swing.JButton();
        c = new javax.swing.JButton();
        x = new javax.swing.JButton();
        d = new javax.swing.JButton();
        y = new javax.swing.JButton();
        e = new javax.swing.JButton();
        f = new javax.swing.JButton();
        z = new javax.swing.JButton();
        g = new javax.swing.JButton();
        h = new javax.swing.JButton();
        i = new javax.swing.JButton();
        j = new javax.swing.JButton();
        k = new javax.swing.JButton();
        l = new javax.swing.JButton();
        m = new javax.swing.JButton();
        n = new javax.swing.JButton();
        o = new javax.swing.JButton();
        p = new javax.swing.JButton();
        q = new javax.swing.JButton();
        r = new javax.swing.JButton();
        s = new javax.swing.JButton();
        palavra_view = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        new_game = new javax.swing.JMenu();
        novo_jogo = new javax.swing.JMenuItem();
        adicionar_palavra_button = new javax.swing.JMenuItem();

        adicionar_palavra.setMinimumSize(new java.awt.Dimension(614, 315));
        adicionar_palavra.setResizable(false);

        x1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        x1.setText("X");
        x1.setFocusPainted(false);
        x1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                x1ActionPerformed(evt);
            }
        });

        d1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        d1.setText("D");
        d1.setFocusPainted(false);
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });

        y1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        y1.setText("Y");
        y1.setFocusPainted(false);
        y1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                y1ActionPerformed(evt);
            }
        });

        e1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        e1.setText("E");
        e1.setFocusPainted(false);
        e1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e1ActionPerformed(evt);
            }
        });

        f1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f1.setText("F");
        f1.setFocusPainted(false);
        f1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f1ActionPerformed(evt);
            }
        });

        z1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        z1.setText("Z");
        z1.setFocusPainted(false);
        z1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z1ActionPerformed(evt);
            }
        });

        g1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        g1.setText("G");
        g1.setFocusPainted(false);
        g1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g1ActionPerformed(evt);
            }
        });

        h1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        h1.setText("H");
        h1.setFocusPainted(false);
        h1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                h1ActionPerformed(evt);
            }
        });

        i1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        i1.setText("I");
        i1.setFocusPainted(false);
        i1.setPreferredSize(new java.awt.Dimension(39, 23));
        i1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i1ActionPerformed(evt);
            }
        });

        j1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        j1.setText("J");
        j1.setFocusPainted(false);
        j1.setPreferredSize(new java.awt.Dimension(39, 23));
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });

        jButton2.setText("Backspace");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        t1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        t1.setText("T");
        t1.setFocusPainted(false);
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });

        u1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        u1.setText("U");
        u1.setFocusPainted(false);
        u1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u1ActionPerformed(evt);
            }
        });

        a1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        a1.setText("A");
        a1.setFocusPainted(false);
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });

        v1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        v1.setText("V");
        v1.setFocusPainted(false);
        v1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v1ActionPerformed(evt);
            }
        });

        b1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b1.setText("B");
        b1.setFocusPainted(false);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        w1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        w1.setText("W");
        w1.setFocusPainted(false);
        w1.setPreferredSize(new java.awt.Dimension(39, 23));
        w1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                w1ActionPerformed(evt);
            }
        });

        c1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c1.setText("C");
        c1.setFocusPainted(false);
        c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c1ActionPerformed(evt);
            }
        });

        k1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        k1.setText("K");
        k1.setFocusPainted(false);
        k1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                k1ActionPerformed(evt);
            }
        });

        l1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l1.setText("L");
        l1.setFocusPainted(false);
        l1.setPreferredSize(new java.awt.Dimension(39, 23));
        l1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                l1ActionPerformed(evt);
            }
        });

        m1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m1.setText("M");
        m1.setFocusPainted(false);
        m1.setPreferredSize(new java.awt.Dimension(39, 23));
        m1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m1ActionPerformed(evt);
            }
        });

        n1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        n1.setText("N");
        n1.setFocusPainted(false);
        n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n1ActionPerformed(evt);
            }
        });

        o1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        o1.setText("O");
        o1.setFocusPainted(false);
        o1.setPreferredSize(new java.awt.Dimension(39, 23));
        o1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o1ActionPerformed(evt);
            }
        });

        p1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        p1.setText("P");
        p1.setFocusPainted(false);
        p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1ActionPerformed(evt);
            }
        });

        q1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        q1.setText("Q");
        q1.setFocusPainted(false);
        q1.setPreferredSize(new java.awt.Dimension(39, 23));
        q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q1ActionPerformed(evt);
            }
        });

        r1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        r1.setText("R");
        r1.setFocusPainted(false);
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        s1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        s1.setText("S");
        s1.setFocusPainted(false);
        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1ActionPerformed(evt);
            }
        });

        user_word.setEditable(false);
        user_word.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        user_word.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_word.setText("INFORME UMA PALAVRA");

        javax.swing.GroupLayout adicionar_palavraLayout = new javax.swing.GroupLayout(adicionar_palavra.getContentPane());
        adicionar_palavra.getContentPane().setLayout(adicionar_palavraLayout);
        adicionar_palavraLayout.setHorizontalGroup(
            adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adicionar_palavraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(adicionar_palavraLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(g1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(h1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(k1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adicionar_palavraLayout.createSequentialGroup()
                        .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(w1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(r1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(y1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(u1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(o1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adicionar_palavraLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(z1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(adicionar_palavraLayout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(adicionar_palavraLayout.createSequentialGroup()
                                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(v1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(user_word)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        adicionar_palavraLayout.setVerticalGroup(
            adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionar_palavraLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(user_word, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(w1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(e1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(y1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(u1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(g1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(h1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(z1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(v1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(m1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(adicionar_palavraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Forca - v0.1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        forca_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hangman/forca_00.png"))); // NOI18N
        getContentPane().add(forca_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 11, -1, -1));

        t.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        t.setText("T");
        t.setFocusPainted(false);
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });
        getContentPane().add(t, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 54, 28));

        dica.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dica.setText("Dica: Clique em 'Novo Jogo'");
        getContentPane().add(dica, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 413, 710, -1));

        u.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        u.setText("U");
        u.setFocusPainted(false);
        u.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uActionPerformed(evt);
            }
        });
        getContentPane().add(u, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 54, 28));

        a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        a.setText("A");
        a.setFocusPainted(false);
        a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aActionPerformed(evt);
            }
        });
        getContentPane().add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 54, 28));

        v.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        v.setText("V");
        v.setFocusPainted(false);
        v.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vActionPerformed(evt);
            }
        });
        getContentPane().add(v, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, 54, 28));

        b.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b.setText("B");
        b.setFocusPainted(false);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });
        getContentPane().add(b, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 570, 54, 28));

        w.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        w.setText("W");
        w.setFocusPainted(false);
        w.setPreferredSize(new java.awt.Dimension(39, 23));
        w.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wActionPerformed(evt);
            }
        });
        getContentPane().add(w, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 54, 28));

        c.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c.setText("C");
        c.setFocusPainted(false);
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });
        getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, 54, 28));

        x.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        x.setText("X");
        x.setFocusPainted(false);
        x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xActionPerformed(evt);
            }
        });
        getContentPane().add(x, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, 54, 28));

        d.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        d.setText("D");
        d.setFocusPainted(false);
        d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dActionPerformed(evt);
            }
        });
        getContentPane().add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 54, 28));

        y.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        y.setText("Y");
        y.setFocusPainted(false);
        y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yActionPerformed(evt);
            }
        });
        getContentPane().add(y, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, 54, 28));

        e.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        e.setText("E");
        e.setFocusPainted(false);
        e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eActionPerformed(evt);
            }
        });
        getContentPane().add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 54, 28));

        f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        f.setText("F");
        f.setFocusPainted(false);
        f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fActionPerformed(evt);
            }
        });
        getContentPane().add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 54, 28));

        z.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        z.setText("Z");
        z.setFocusPainted(false);
        z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zActionPerformed(evt);
            }
        });
        getContentPane().add(z, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 570, 54, 28));

        g.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        g.setText("G");
        g.setFocusPainted(false);
        g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gActionPerformed(evt);
            }
        });
        getContentPane().add(g, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, 54, 28));

        h.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        h.setText("H");
        h.setFocusPainted(false);
        h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hActionPerformed(evt);
            }
        });
        getContentPane().add(h, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, 54, 28));

        i.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        i.setText("I");
        i.setFocusPainted(false);
        i.setPreferredSize(new java.awt.Dimension(39, 23));
        i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iActionPerformed(evt);
            }
        });
        getContentPane().add(i, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, 54, 28));

        j.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        j.setText("J");
        j.setFocusPainted(false);
        j.setPreferredSize(new java.awt.Dimension(39, 23));
        j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActionPerformed(evt);
            }
        });
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, 54, 28));

        k.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        k.setText("K");
        k.setFocusPainted(false);
        k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kActionPerformed(evt);
            }
        });
        getContentPane().add(k, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 530, 54, 28));

        l.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l.setText("L");
        l.setFocusPainted(false);
        l.setPreferredSize(new java.awt.Dimension(39, 23));
        l.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lActionPerformed(evt);
            }
        });
        getContentPane().add(l, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 54, 28));

        m.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m.setText("M");
        m.setFocusPainted(false);
        m.setPreferredSize(new java.awt.Dimension(39, 23));
        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });
        getContentPane().add(m, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 570, 54, 28));

        n.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        n.setText("N");
        n.setFocusPainted(false);
        n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nActionPerformed(evt);
            }
        });
        getContentPane().add(n, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 570, 54, 28));

        o.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        o.setText("O");
        o.setFocusPainted(false);
        o.setPreferredSize(new java.awt.Dimension(39, 23));
        o.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oActionPerformed(evt);
            }
        });
        getContentPane().add(o, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 54, 28));

        p.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        p.setText("P");
        p.setFocusPainted(false);
        p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pActionPerformed(evt);
            }
        });
        getContentPane().add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 54, 28));

        q.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        q.setText("Q");
        q.setFocusPainted(false);
        q.setPreferredSize(new java.awt.Dimension(39, 23));
        q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qActionPerformed(evt);
            }
        });
        getContentPane().add(q, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 54, 28));

        r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        r.setText("R");
        r.setFocusPainted(false);
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });
        getContentPane().add(r, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 54, 28));

        s.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        s.setText("S");
        s.setFocusPainted(false);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });
        getContentPane().add(s, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 54, 28));

        palavra_view.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        palavra_view.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        palavra_view.setText(" ");
        palavra_view.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(palavra_view, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 710, -1));

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/color.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 800, 200));

        jMenuBar2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        new_game.setText("Menu");
        new_game.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        novo_jogo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        novo_jogo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        novo_jogo.setText("Novo Jogo");
        novo_jogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novo_jogoActionPerformed(evt);
            }
        });
        new_game.add(novo_jogo);

        adicionar_palavra_button.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        adicionar_palavra_button.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adicionar_palavra_button.setText("Adicionar Palavra");
        adicionar_palavra_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionar_palavra_buttonActionPerformed(evt);
            }
        });
        new_game.add(adicionar_palavra_button);

        jMenuBar2.add(new_game);

        setJMenuBar(jMenuBar2);

        setSize(new java.awt.Dimension(738, 676));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void novo_jogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novo_jogoActionPerformed
        if (!end_game)
        {
            int s_n = JOptionPane.showConfirmDialog(null, "Você está com um jogo em andamento.\nTem certeza que deseja iniciar um novo jogo?", "Aviso", JOptionPane.WARNING_MESSAGE);
            if (s_n == 0)
            {
                reinicia();
            }
        }
        else
        {
            reinicia();
        }
    }//GEN-LAST:event_novo_jogoActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aActionPerformed
        letra = "A";
        a.setEnabled(false);
        confere();
    }//GEN-LAST:event_aActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        letra = "B";
        b.setEnabled(false);
        confere();
    }//GEN-LAST:event_bActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
        letra = "C";
        c.setEnabled(false);
        confere();
    }//GEN-LAST:event_cActionPerformed

    private void dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dActionPerformed
        letra = "D";
        d.setEnabled(false);
        confere();
    }//GEN-LAST:event_dActionPerformed

    private void eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eActionPerformed
        letra = "E";
        e.setEnabled(false);
        confere();
    }//GEN-LAST:event_eActionPerformed

    private void fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fActionPerformed
        letra = "F";
        f.setEnabled(false);
        confere();
    }//GEN-LAST:event_fActionPerformed

    private void gActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gActionPerformed
        letra = "G";
        g.setEnabled(false);
        confere();
    }//GEN-LAST:event_gActionPerformed

    private void hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hActionPerformed
        letra = "H";
        h.setEnabled(false);
        confere();
    }//GEN-LAST:event_hActionPerformed

    private void iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iActionPerformed
        letra = "I";
        i.setEnabled(false);
        confere();
    }//GEN-LAST:event_iActionPerformed

    private void jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActionPerformed
        letra = "J";
        j.setEnabled(false);
        confere();
    }//GEN-LAST:event_jActionPerformed

    private void kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kActionPerformed
        letra = "K";
        k.setEnabled(false);
        confere();
    }//GEN-LAST:event_kActionPerformed

    private void lActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lActionPerformed
        letra = "L";
        l.setEnabled(false);
        confere();
    }//GEN-LAST:event_lActionPerformed

    private void mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mActionPerformed
        letra = "M";
        m.setEnabled(false);
        confere();
    }//GEN-LAST:event_mActionPerformed

    private void nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nActionPerformed
        letra = "N";
        n.setEnabled(false);
        confere();
    }//GEN-LAST:event_nActionPerformed

    private void oActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oActionPerformed
        letra = "O";
        o.setEnabled(false);
        confere();
    }//GEN-LAST:event_oActionPerformed

    private void pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pActionPerformed
        letra = "P";
        p.setEnabled(false);
        confere();
    }//GEN-LAST:event_pActionPerformed

    private void qActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qActionPerformed
        letra = "Q";
        q.setEnabled(false);
        confere();
    }//GEN-LAST:event_qActionPerformed

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        letra = "R";
        r.setEnabled(false);
        confere();
    }//GEN-LAST:event_rActionPerformed

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        letra = "S";
        s.setEnabled(false);
        confere();
    }//GEN-LAST:event_sActionPerformed

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
        letra = "T";
        t.setEnabled(false);
        confere();
    }//GEN-LAST:event_tActionPerformed

    private void uActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uActionPerformed
        letra = "U";
        u.setEnabled(false);
        confere();
    }//GEN-LAST:event_uActionPerformed

    private void vActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vActionPerformed
        letra = "V";
        v.setEnabled(false);
        confere();
    }//GEN-LAST:event_vActionPerformed

    private void wActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wActionPerformed
        letra = "W";
        w.setEnabled(false);
        confere();
    }//GEN-LAST:event_wActionPerformed

    private void xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xActionPerformed
        letra = "X";
        x.setEnabled(false);
        confere();
    }//GEN-LAST:event_xActionPerformed

    private void yActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yActionPerformed
        letra = "Y";
        y.setEnabled(false);
        confere();
    }//GEN-LAST:event_yActionPerformed

    private void zActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zActionPerformed
        letra = "Z";
        z.setEnabled(false);
        confere();
    }//GEN-LAST:event_zActionPerformed

    private void adicionar_palavra_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionar_palavra_buttonActionPerformed
        adicionar_palavra.setVisible(true);
        adicionar_palavra.setLocationRelativeTo(null);
    }//GEN-LAST:event_adicionar_palavra_buttonActionPerformed

    private void x1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_x1ActionPerformed
        letra = "X";
        max_caracter();
    }//GEN-LAST:event_x1ActionPerformed

    private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
        letra = "D";
        max_caracter();
    }//GEN-LAST:event_d1ActionPerformed

    private void y1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_y1ActionPerformed
        letra = "Y";
        max_caracter();
    }//GEN-LAST:event_y1ActionPerformed

    private void e1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e1ActionPerformed
        letra = "E";
        max_caracter();
    }//GEN-LAST:event_e1ActionPerformed

    private void f1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f1ActionPerformed
        letra = "F";
        max_caracter();
    }//GEN-LAST:event_f1ActionPerformed

    private void z1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z1ActionPerformed
        letra = "Z";
        max_caracter();
    }//GEN-LAST:event_z1ActionPerformed

    private void g1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g1ActionPerformed
        letra = "G";
        max_caracter();
    }//GEN-LAST:event_g1ActionPerformed

    private void h1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_h1ActionPerformed
        letra = "H";
        max_caracter();
    }//GEN-LAST:event_h1ActionPerformed

    private void i1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i1ActionPerformed
        letra = "I";
        max_caracter();
    }//GEN-LAST:event_i1ActionPerformed

    private void j1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ActionPerformed
        letra = "J";
        max_caracter();
    }//GEN-LAST:event_j1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            int size;
            size = temp.length();
            String temp2 = temp.substring(0, size-1);
            temp = temp2;
            user_word.setText(temp);
        }catch(Exception e){
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    //ESCREVE NO ARQUIVO
        if (temp.length() <= 1)
        {
            JOptionPane.showMessageDialog(null, "Informe ao menos 2 caracteres!", "Aviso", JOptionPane.ERROR_MESSAGE);
            
        }
        else{
            if (op == 0){
                word_dica = temp;
                op = op + 1;
                user_word.setText("INFORME UMA DICA PARA A PALAVRA");
            }
            else{
                word_dica = word_dica + "#" +temp;
                try{
                    FileWriter arquivo = new FileWriter(new File("src/palavras.txt"), true );
                    PrintWriter escrever = new PrintWriter(arquivo);
                    escrever.println(word_dica);
                    arquivo.close();

                    BufferedReader leitor_buffer = new BufferedReader (new FileReader ("src/palavras.txt"));
                    while(leitor_buffer.ready()){
                        String linha = leitor_buffer.readLine(); // lê até a última linha
                    }
                    leitor_buffer.close();
                }catch(Exception ex){}
                int s_n = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais palavras?", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.out.println(s_n);
                if(s_n == 0)
                {
                    user_word.setText("INFORME UMA PALAVRA");
                    op = 0;
                }
                else
                {
                    user_word.setText("INFORME UMA PALAVRA");
                    adicionar_palavra.setVisible(false);
                    op = 0;
                }

            }
            temp = "";
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        letra = "T";
        max_caracter();
    }//GEN-LAST:event_t1ActionPerformed

    private void u1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u1ActionPerformed
        letra = "U";
        max_caracter();
    }//GEN-LAST:event_u1ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        letra = "A";
        max_caracter();
    }//GEN-LAST:event_a1ActionPerformed

    private void v1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v1ActionPerformed
        letra = "V";
        max_caracter();
    }//GEN-LAST:event_v1ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        letra = "B";
        max_caracter();
    }//GEN-LAST:event_b1ActionPerformed

    private void w1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_w1ActionPerformed
        letra = "W";
        max_caracter();
    }//GEN-LAST:event_w1ActionPerformed

    private void c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c1ActionPerformed
        letra = "C";
        max_caracter();
    }//GEN-LAST:event_c1ActionPerformed

    private void k1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_k1ActionPerformed
        letra = "K";
        max_caracter();
    }//GEN-LAST:event_k1ActionPerformed

    private void l1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_l1ActionPerformed
        letra = "L";
        max_caracter();
    }//GEN-LAST:event_l1ActionPerformed

    private void m1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m1ActionPerformed
        letra = "M";
        max_caracter();
    }//GEN-LAST:event_m1ActionPerformed

    private void n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n1ActionPerformed
        letra = "N";
        max_caracter();
    }//GEN-LAST:event_n1ActionPerformed

    private void o1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o1ActionPerformed
        letra = "O";
        max_caracter();
    }//GEN-LAST:event_o1ActionPerformed

    private void p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1ActionPerformed
        letra = "P";
        max_caracter();
    }//GEN-LAST:event_p1ActionPerformed

    private void q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q1ActionPerformed
        letra = "Q";
        max_caracter();
    }//GEN-LAST:event_q1ActionPerformed

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        letra = "R";
        max_caracter();
    }//GEN-LAST:event_r1ActionPerformed

    private void s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1ActionPerformed
        letra = "S";
        max_caracter();
    }//GEN-LAST:event_s1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton a;
    private javax.swing.JButton a1;
    private javax.swing.JDialog adicionar_palavra;
    private javax.swing.JMenuItem adicionar_palavra_button;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton c;
    private javax.swing.JButton c1;
    private javax.swing.JButton d;
    private javax.swing.JButton d1;
    private javax.swing.JLabel dica;
    private javax.swing.JButton e;
    private javax.swing.JButton e1;
    private javax.swing.JButton f;
    private javax.swing.JButton f1;
    private javax.swing.JLabel forca_image;
    private javax.swing.JButton g;
    private javax.swing.JButton g1;
    private javax.swing.JButton h;
    private javax.swing.JButton h1;
    private javax.swing.JButton i;
    private javax.swing.JButton i1;
    private javax.swing.JButton j;
    private javax.swing.JButton j1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton k;
    private javax.swing.JButton k1;
    private javax.swing.JButton l;
    private javax.swing.JButton l1;
    private javax.swing.JButton m;
    private javax.swing.JButton m1;
    private javax.swing.JButton n;
    private javax.swing.JButton n1;
    private javax.swing.JMenu new_game;
    private javax.swing.JMenuItem novo_jogo;
    private javax.swing.JButton o;
    private javax.swing.JButton o1;
    private javax.swing.JButton p;
    private javax.swing.JButton p1;
    private javax.swing.JLabel palavra_view;
    private javax.swing.JButton q;
    private javax.swing.JButton q1;
    private javax.swing.JButton r;
    private javax.swing.JButton r1;
    private javax.swing.JButton s;
    private javax.swing.JButton s1;
    private javax.swing.JButton t;
    private javax.swing.JButton t1;
    private javax.swing.JButton u;
    private javax.swing.JButton u1;
    private javax.swing.JTextField user_word;
    private javax.swing.JButton v;
    private javax.swing.JButton v1;
    private javax.swing.JButton w;
    private javax.swing.JButton w1;
    private javax.swing.JButton x;
    private javax.swing.JButton x1;
    private javax.swing.JButton y;
    private javax.swing.JButton y1;
    private javax.swing.JButton z;
    private javax.swing.JButton z1;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/forca.png")));
    }  
}