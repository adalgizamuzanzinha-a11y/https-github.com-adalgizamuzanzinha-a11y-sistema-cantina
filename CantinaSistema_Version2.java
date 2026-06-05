package ui;

import models.*;
import utils.Constants;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class CantinaSistema extends JFrame {
    private List<Produto> produtos;
    private List<Venda> vendas;
    private DefaultTableModel tableModel;
    private JTable table;
    private int nextProdutoId = 1;
    private int nextVendaId = 1;
    private JLabel statusLabel;
    private JLabel relogioLabel;
    private Timer timer;
    private JComboBox<String> categoriaFiltro;
    private String usuarioLogado;
    private String tipoUsuario;
    private boolean vendedorAutenticado = false;
    
    public CantinaSistema() {
        produtos = new ArrayList<>();
        vendas = new ArrayList<>();
        inicializarProdutos();
        aplicarCSS();
        mostrarTelaLogin();
    }
    
    private void inicializarProdutos() {
        Produto p1 = new Produto(nextProdutoId++, "Coxinha", 550, 30, "Salgados");
        p1.adicionarVariedade("Frango", 15);
        p1.adicionarVariedade("Carne", 10);
        p1.adicionarVariedade("Queijo", 5);
        produtos.add(p1);
        
        Produto p2 = new Produto(nextProdutoId++, "Suco Natural", 600, 25, "Bebidas");
        p2.adicionarVariedade("Laranja", 10);
        p2.adicionarVariedade("Manga", 8);
        p2.adicionarVariedade("Maracujá", 7);
        produtos.add(p2);
        
        Produto p3 = new Produto(nextProdutoId++, "Sanduíche", 800, 20, "Salgados");
        p3.adicionarVariedade("Frango", 8);
        p3.adicionarVariedade("Carne", 7);
        p3.adicionarVariedade("Vegetariano", 5);
        produtos.add(p3);
        
        produtos.add(new Produto(nextProdutoId++, "Refrigerante", 400, 40, "Bebidas"));
        produtos.add(new Produto(nextProdutoId++, "Brigadeiro", 350, 30, "Doces"));
        produtos.add(new Produto(nextProdutoId++, "Pão de Queijo", 450, 25, "Salgados"));
        produtos.add(new Produto(nextProdutoId++, "Água Mineral", 250, 50, "Bebidas"));
        produtos.add(new Produto(nextProdutoId++, "Bolo de Chocolate", 700, 15, "Doces"));
        produtos.add(new Produto(nextProdutoId++, "Pastel", 500, 20, "Salgados"));
        produtos.add(new Produto(nextProdutoId++, "Café", 300, 40, "Bebidas"));
    }
    
    private void aplicarCSS() {
        try {
            Font font = new Font(Constants.FONTE_SISTEMA, Font.PLAIN, 12);
            UIManager.put("Button.font", font);
            UIManager.put("Label.font", font);
            UIManager.put("Table.font", font);
            UIManager.put("ComboBox.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("PasswordField.font", font);
            
            UIManager.put("Panel.background", Constants.COR_FUNDO);
            UIManager.put("Button.background", Constants.COR_PRIMARIA);
            UIManager.put("Button.foreground", Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarTelaLogin() {
        setTitle(Constants.TITULO_PRINCIPAL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Constants.COR_FUNDO);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Constants.COR_FUNDO);
        JLabel logoLabel = new JLabel();
        logoLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 32));
        logoLabel.setForeground(Constants.COR_PRIMARIA);
        logoLabel.setText("🏫 CH");
        logoPanel.add(logoLabel);
        
        JLabel nomeEquipeLabel = new JLabel(Constants.NOME_EQUIPE);
        nomeEquipeLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 20));
        nomeEquipeLabel.setForeground(Constants.COR_SECUNDARIA);
        nomeEquipeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel sloganLabel = new JLabel(Constants.SLOGAN);
        sloganLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.ITALIC, 12));
        sloganLabel.setForeground(Color.GRAY);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel("Sistema de Gerenciamento");
        titleLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 20));
        titleLabel.setForeground(Constants.COR_SECUNDARIA);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Cantina Escolar");
        subtitleLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(logoPanel);
        loginPanel.add(nomeEquipeLabel);
        loginPanel.add(sloganLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(titleLabel);
        loginPanel.add(subtitleLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Campo de nome
        JPanel nomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nomePanel.setBackground(Constants.COR_FUNDO);
        JLabel nomeLabel = new JLabel("👤 Nome: ");
        nomeLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 14));
        JTextField nomeField = new JTextField(20);
        nomeField.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 14));
        nomePanel.add(nomeLabel);
        nomePanel.add(nomeField);
        
        // Tipo de usuário
        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tipoPanel.setBackground(Constants.COR_FUNDO);
        JLabel tipoLabel = new JLabel("🎭 Tipo: ");
        tipoLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 14));
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"CLIENTE", "VENDEDOR"});
        tipoCombo.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 14));
        tipoPanel.add(tipoLabel);
        tipoPanel.add(tipoCombo);
        
        // Campo de senha
        JPanel senhaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        senhaPanel.setBackground(Constants.COR_FUNDO);
        JLabel senhaLabel = new JLabel("🔒 Senha: ");
        senhaLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 14));
        JPasswordField senhaField = new JPasswordField(15);
        senhaField.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 14));
        senhaPanel.add(senhaLabel);
        senhaPanel.add(senhaField);
        senhaPanel.setVisible(false);
        
        tipoCombo.addActionListener(e -> {
            senhaPanel.setVisible(tipoCombo.getSelectedItem().equals("VENDEDOR"));
            loginPanel.revalidate();
            loginPanel.repaint();
        });
        
        // Botão entrar
        JButton entrarButton = criarBotao("ENTRAR NO SISTEMA", Constants.COR_SECUNDARIA, Constants.ALTURA_BOTAO, Constants.LARGURA_BOTAO);
        
        entrarButton.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite seu nome!");
                return;
            }
            
            String tipo = (String) tipoCombo.getSelectedItem();
            
            if (tipo.equals("VENDEDOR")) {
                String senha = new String(senhaField.getPassword());
                if (!senha.equals(Constants.SENHA_VENDEDOR)) {
                    JOptionPane.showMessageDialog(this, "❌ Senha incorreta!\nAcesso negado para VENDEDOR.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                vendedorAutenticado = true;
            }
            
            usuarioLogado = nome;
            tipoUsuario = tipo;
            iniciarSistemaPrincipal();
        });
        
        loginPanel.add(nomePanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        loginPanel.add(tipoPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        loginPanel.add(senhaPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(entrarButton);
        
        // Informações de contato
        JPanel contatoPanel = new JPanel();
        contatoPanel.setBackground(Constants.COR_FUNDO);
        contatoPanel.setLayout(new BoxLayout(contatoPanel, BoxLayout.Y_AXIS));
        
        JLabel contatoTitle = new JLabel("📞 CONTATO:");
        contatoTitle.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 12));
        contatoTitle.setForeground(Constants.COR_PRIMARIA);
        contatoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel emailLabel = new JLabel("✉️ Email: " + Constants.EMAIL_CONTATO);
        emailLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 11));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel telefoneLabel = new JLabel("📱 Telefone: " + Constants.TELEFONE_CONTATO);
        telefoneLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 11));
        telefoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contatoPanel.add(contatoTitle);
        contatoPanel.add(emailLabel);
        contatoPanel.add(telefoneLabel);
        
        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Constants.COR_FUNDO);
        JLabel footerLabel = new JLabel("© 2024 Conexão Harvard - Todos os direitos reservados");
        footerLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.PLAIN, 10));
        footerLabel.setForeground(Color.GRAY);
        footerPanel.add(footerLabel);
        
        add(loginPanel, BorderLayout.CENTER);
        add(contatoPanel, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void iniciarSistemaPrincipal() {
        getContentPane().removeAll();
        initUI();
        setVisible(true);
    }
    
    private void iniciarRelogio() {
        timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            relogioLabel.setText("🕐 " + sdf.format(new Date()));
        });
        timer.start();
    }
    
    /**
     * Inicializa a interface principal
     */
    private void initUI() {
        setTitle(Constants.TITULO_PRINCIPAL + " - " + tipoUsuario + ": " + usuarioLogado);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Constants.COR_PRIMARIA);
        
        JMenu menuArquivo = new JMenu("📁 Arquivo");
        menuArquivo.setForeground(Color.black);
        JMenuItem menuSair = new JMenuItem("🚪 Sair");
        menuSair.addActionListener(e -> confirmarSair());
        menuArquivo.add(menuSair);
        
        JMenu menuRelatorios = new JMenu("📊 Relatórios");
        menuRelatorios.setForeground(Color.black);
        JMenuItem menuRelVendas = new JMenuItem("📈 Relatório de Vendas");
        JMenuItem menuRelProdutos = new JMenuItem("📦 Relatório de Produtos");
        menuRelVendas.addActionListener(e -> mostrarRelatorioVendas());
        menuRelProdutos.addActionListener(e -> mostrarRelatorioProdutos());
        menuRelatorios.add(menuRelVendas);
        menuRelatorios.add(menuRelProdutos);
        
        JMenu menuContato = new JMenu("📞 Contato");
        menuContato.setForeground(Color.black);
        JMenuItem menuInfoContato = new JMenuItem("📱 Informações de Contato");
        menuInfoContato.addActionListener(e -> mostrarContato());
        menuContato.add(menuInfoContato);
        
        menuBar.add(menuArquivo);
        menuBar.add(menuRelatorios);
        menuBar.add(menuContato);
        
        JLabel logoMenu = new JLabel("🏫 " + Constants.NOME_EQUIPE);
        logoMenu.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 14));
        logoMenu.setForeground(new Color(255, 215, 0));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logoMenu);
        
        setJMenuBar(menuBar);
        
        // Painel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Constants.COR_SECUNDARIA);
        
        JLabel titleLabel = new JLabel("🏪 CANTINA ESCOLAR - SISTEMA DE GESTÃO 🏪");
        titleLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.setBackground(Constants.COR_SECUNDARIA);
        JLabel userLabel = new JLabel("👤 " + tipoUsuario + ": " + usuarioLogado);
        userLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 12));
        userLabel.setForeground(Color.WHITE);
        userPanel.add(userLabel);
        
        relogioLabel = new JLabel("Carregando...");
        relogioLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 12));
        relogioLabel.setForeground(Color.WHITE);
        userPanel.add(relogioLabel);
        
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(userPanel, BorderLayout.EAST);
        
        // Painel de boas-vindas
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(255, 193, 7));
        String mensagemBoasVindas = tipoUsuario.equals("VENDEDOR") ? 
            "✨ Bem-vindo Vendedor! Você tem acesso total ao sistema! ✨" :
            "✨ Bem-vindo Cliente! Aproveite nossas promoções e faça seu pedido! ✨";
        JLabel welcomeLabel = new JLabel(mensagemBoasVindas);
        welcomeLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 14));
        welcomeLabel.setForeground(Constants.COR_SECUNDARIA);
        welcomePanel.add(welcomeLabel);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 5, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        JButton btnAddProduto = criarBotao("➕ Adicionar Produto", Constants.COR_SUCESSO, 45, 150);
        JButton btnListarProdutos = criarBotao("📋 Listar Produtos", Constants.COR_SUCESSO, 45, 150);
        JButton btnNovaVenda = criarBotao("💰 Nova Venda", Constants.COR_ALERTA, 45, 150);
        JButton btnRelatorioVendas = criarBotao("📊 Relatório Vendas", new Color(156, 39, 176), 45, 150);
        JButton btnAtualizarEstoque = criarBotao("📦 Atualizar Estoque", new Color(121, 85, 72), 45, 150);
        JButton btnProdutosBaixoEstoque = criarBotao("⚠️ Baixo Estoque", Constants.COR_ERRO, 45, 150);
        JButton btnPromocoes = criarBotao("🎯 Promoções", new Color(255, 87, 34), 45, 150);
        JButton btnLimparTabela = criarBotao("🔄 Limpar Filtro", new Color(96, 125, 139), 45, 150);
        JButton btnCardapioDigital = criarBotao("📱 Cardápio", new Color(63, 81, 181), 45, 150);
        JButton btnSair = criarBotao("🚪 Sair", new Color(158, 158, 158), 45, 150);
        
        if (tipoUsuario.equals("VENDEDOR") && vendedorAutenticado) {
            buttonPanel.add(btnAddProduto);
            buttonPanel.add(btnListarProdutos);
            buttonPanel.add(btnNovaVenda);
            buttonPanel.add(btnRelatorioVendas);
            buttonPanel.add(btnAtualizarEstoque);
            buttonPanel.add(btnProdutosBaixoEstoque);
            buttonPanel.add(btnPromocoes);
            buttonPanel.add(btnLimparTabela);
            buttonPanel.add(btnCardapioDigital);
            buttonPanel.add(btnSair);
        } else {
            buttonPanel.setLayout(new GridLayout(1, 4, 15, 15));
            buttonPanel.add(btnCardapioDigital);
            buttonPanel.add(btnNovaVenda);
            buttonPanel.add(btnPromocoes);
            buttonPanel.add(btnSair);
        }
        
        // Painel de filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBorder(BorderFactory.createTitledBorder("🔍 Filtrar por Categoria"));
        filterPanel.add(new JLabel("Categoria:"));
        
        categoriaFiltro = new JComboBox<>(new String[]{"Todos", "Salgados", "Doces", "Bebidas"});
        categoriaFiltro.addActionListener(e -> filtrarPorCategoria());
        
        JButton btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.addActionListener(e -> filtrarPorCategoria());
        
        filterPanel.add(categoriaFiltro);
        filterPanel.add(btnBuscar);
        
        // Tabela
        String[] colunas = {"ID", "Produto", "Variedades", "Categoria", "Preço (KZ)", "Estoque", "Valor Total"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFont(new Font(Constants.FONTE_SISTEMA, Font.PLAIN, 14));
        table.setRowHeight(Constants.ALTURA_LINHA_TABELA);
        table.getTableHeader().setFont(new Font(Constants.FONTE_SISTEMA, Font.BOLD, 16));
        table.getTableHeader().setBackground(Constants.COR_SECUNDARIA);
        table.getTableHeader().setForeground(Color.BLUE);
        
        if (tipoUsuario.equals("VENDEDOR") && vendedorAutenticado) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int row = table.getSelectedRow();
                        if (row != -1) {
                            int id = (int) tableModel.getValueAt(row, 0);
                            editarProduto(id);
                        }
                    }
                }
            });
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📋 Catálogo de Produtos"));
        
        // Painel de estatísticas
        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statsPanel.setBackground(new Color(227, 242, 253));
        
        statusLabel = new JLabel("📊 Sistema pronto", SwingConstants.CENTER);
        statusLabel.setFont(new Font(Constants.FONTE_PRINCIPAL, Font.BOLD, 12));
        statsPanel.add(statusLabel);
        
        // Adicionar componentes
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(welcomePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.NORTH);
        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
        
        // Ações dos botões
        if (tipoUsuario.equals("VENDEDOR") && vendedorAutenticado) {
            btnAddProduto.addActionListener(e -> adicionarProduto());
            btnListarProdutos.addActionListener(e -> listarProdutos());
            btnAtualizarEstoque.addActionListener(e -> atualizarEstoque());
            btnProdutosBaixoEstoque.addActionListener(e -> mostrarBaixoEstoque());
            btnRelatorioVendas.addActionListener(e -> mostrarRelatorioVendas());
        }
        
        btnNovaVenda.addActionListener(e -> novaVenda());
        btnPromocoes.addActionListener(e -> mostrarPromocoes());
        btnLimparTabela.addActionListener(e -> listarProdutos());
        btnCardapioDigital.addActionListener(e -> mostrarCardapioDigital());
        btnSair.addActionListener(e -> confirmarSair());
        
        iniciarRelogio();
        listarProdutos();
        atualizarEstatisticas();
    }
    
    private JButton criarBotao(String texto, Color cor, int altura, int largura) {
        JButton botao = new JButton(texto);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font(Constants.FONTE_SISTEMA, Font.BOLD, 12));
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (altura > 0 && largura > 0) {
            botao.setPreferredSize(new Dimension(largura, altura));
        }
        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(cor.darker());
            }
            public void mouseExited(MouseEvent e) {
                botao.setBackground(cor);
            }
        });
        return botao;
    }
    
    private void listarProdutos() {
        tableModel.setRowCount(0);
        double valorTotalEstoque = 0;
        
        for (Produto p : produtos) {
            double valorProdutoEstoque = p.getPreco() * p.getQuantidade();
            valorTotalEstoque += valorProdutoEstoque;
            
            String variedadesStr = "";
            if (!p.getVariedades().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Integer> entry : p.getVariedades().entrySet()) {
                    sb.append(entry.getKey()).append(":").append(entry.getValue()).append(" ");
                }
                variedadesStr = sb.toString();
            } else {
                variedadesStr = "Padrão";
            }
            
            Object[] row = {p.getId(), p.getNome(), variedadesStr, p.getCategoria(), 
                           String.format("%,.0f", p.getPreco()), p.getQuantidade(),
                           String.format("%,.0f", valorProdutoEstoque)};
            tableModel.addRow(row);
        }
        
        atualizarEstatisticas();
    }
    
    private void filtrarPorCategoria() {
        String categoria = (String) categoriaFiltro.getSelectedItem();
        if (categoria.equals("Todos")) {
            listarProdutos();
        } else {
            tableModel.setRowCount(0);
            for (Produto p : produtos) {
                if (p.getCategoria().equals(categoria)) {
                    String variedadesStr = !p.getVariedades().isEmpty() ? p.getVariedades().toString() : "Padrão";
                    Object[] row = {p.getId(), p.getNome(), variedadesStr, p.getCategoria(), 
                                   String.format("%,.0f", p.getPreco()), p.getQuantidade(),
                                   String.format("%,.0f", p.getPreco() * p.getQuantidade())};
                    tableModel.addRow(row);
                }
            }
        }
    }
    
    private void novaVenda() {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Não há produtos disponíveis!");
            return;
        }
        
        String cliente = tipoUsuario.equals("CLIENTE") ? usuarioLogado : 
            JOptionPane.showInputDialog(this, "👤 Nome do cliente:", "Nova Venda", JOptionPane.QUESTION_MESSAGE);
        if (cliente == null || cliente.trim().isEmpty()) {
            cliente = "Cliente Anônimo";
        }
        
        final String nomeCliente = cliente;
        final String nomeVendedor = (tipoUsuario.equals("VENDEDOR") && vendedorAutenticado) ? usuarioLogado : "Sistema";
        
        Venda venda = new Venda(nextVendaId++, nomeCliente, nomeVendedor);
        JDialog dialog = new JDialog(this, "🛒 Nova Venda - Cliente: " + nomeCliente, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(900, 600);
        
        DefaultTableModel vendaTableModel = new DefaultTableModel(new String[]{"Produto", "Variedade", "Preço Unit.", "Quantidade", "Subtotal"}, 0);
        JTable vendaTable = new JTable(vendaTableModel);
        vendaTable.setFont(new Font(Constants.FONTE_SISTEMA, Font.PLAIN, 12));
        vendaTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(vendaTable);
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JComboBox<Produto> produtoCombo = new JComboBox<>(produtos.toArray(new Produto[0]));
        JComboBox<String> variedadeCombo = new JComboBox<>();
        JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        
        produtoCombo.addActionListener(e -> {
            Produto prod = (Produto) produtoCombo.getSelectedItem();
            variedadeCombo.removeAllItems();
            if (prod.getVariedades().isEmpty()) {
                variedadeCombo.addItem("Padrão");
            } else {
                for (String variedade : prod.getVariedades().keySet()) {
                    variedadeCombo.addItem(variedade);
                }
            }
        });
        
        // Inicializar variedades
        Produto primeiroProduto = (Produto) produtoCombo.getSelectedItem();
        if (primeiroProduto.getVariedades().isEmpty()) {
            variedadeCombo.addItem("Padrão");
        } else {
            for (String variedade : primeiroProduto.getVariedades().keySet()) {
                variedadeCombo.addItem(variedade);
            }
        }
        
        formPanel.add(new JLabel("📦 Produto:"));
        formPanel.add(produtoCombo);
        formPanel.add(new JLabel("🎨 Variedade:"));
        formPanel.add(variedadeCombo);
        formPanel.add(new JLabel("🔢 Quantidade:"));
        formPanel.add(quantidadeSpinner);
        
        JButton btnAdicionarItem = criarBotao("➕ Adicionar Item", Constants.COR_SUCESSO, 35, 150);
        JButton btnFinalizar = criarBotao("✅ Finalizar Venda", Constants.COR_ALERTA, 35, 150);
        JButton btnCancelar = criarBotao("❌ Cancelar", Constants.COR_ERRO, 35, 150);
        
        JPanel botoesPanel = new JPanel(new FlowLayout());
        botoesPanel.add(btnAdicionarItem);
        botoesPanel.add(btnFinalizar);
        botoesPanel.add(btnCancelar);
        
        inputPanel.add(formPanel, BorderLayout.CENTER);
        inputPanel.add(botoesPanel, BorderLayout.SOUTH);
        
        JLabel totalLabel = new JLabel("💰 TOTAL: 0 KZ", SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalLabel.setForeground(Constants.COR_SECUNDARIA);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        btnAdicionarItem.addActionListener(e -> {
            try {
                Produto produto = (Produto) produtoCombo.getSelectedItem();
                String variedade = (String) variedadeCombo.getSelectedItem();
                int quantidade = (int) quantidadeSpinner.getValue();
                
                if (quantidade <= 0) {
                    JOptionPane.showMessageDialog(dialog, "⚠️ Quantidade inválida!");
                    return;
                }
                
                if (produto.getQuantidade() >= quantidade) {
                    venda.adicionarItem(produto, quantidade, variedade);
                    Object[] row = {produto.getNome(), variedade, 
                                   String.format("%,.0f", produto.getPreco()), quantidade,
                                   String.format("%,.0f", produto.getPreco() * quantidade)};
                    vendaTableModel.addRow(row);
                    totalLabel.setText(String.format("💰 TOTAL: %,.0f KZ", venda.getTotal()));
                    listarProdutos();
                    
                    JOptionPane.showMessageDialog(dialog, "✅ Item adicionado!");
                } else {
                    JOptionPane.showMessageDialog(dialog, "❌ Estoque insuficiente! Disponível: " + produto.getQuantidade());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "❌ Erro ao adicionar!");
            }
        });
        
        btnFinalizar.addActionListener(e -> {
            if (venda.getItens().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "⚠️ Nenhum item adicionado!");
            } else {
                vendas.add(venda);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                JOptionPane.showMessageDialog(dialog, String.format(
                    "✅ VENDA FINALIZADA!\n\n" +
                    "📝 ID: %d\n" +
                    "👤 Cliente: %s\n" +
                    "👔 Vendedor: %s\n" +
                    "💰 Total: %,.0f KZ\n" +
                    "📅 Data: %s\n\n" +
                    "🎉 Obrigado pela preferência!\n" +
                    "🏫 " + Constants.NOME_EQUIPE + " - Qualidade que conecta!",
                    venda.getId(), nomeCliente, nomeVendedor, venda.getTotal(), sdf.format(new Date())));
                dialog.dispose();
                atualizarEstatisticas();
            }
        });
        
        btnCancelar.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(dialog, "Cancelar venda?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                dialog.dispose();
        });
        
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(inputPanel, BorderLayout.SOUTH);
        dialog.add(totalLabel, BorderLayout.NORTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void editarProduto(int id) {
        Produto produto = produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (produto != null) {
            JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
            JTextField nomeField = new JTextField(produto.getNome());
            JTextField precoField = new JTextField(String.format("%.0f", produto.getPreco()));
            JTextField quantidadeField = new JTextField(String.valueOf(produto.getQuantidade()));
            JComboBox<String> categoriaCombo = new JComboBox<>(Constants.CATEGORIAS);
            categoriaCombo.setSelectedItem(produto.getCategoria());
            
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("Preço (KZ):"));
            panel.add(precoField);
            panel.add(new JLabel("Quantidade:"));
            panel.add(quantidadeField);
            panel.add(new JLabel("Categoria:"));
            panel.add(categoriaCombo);
            
            if (JOptionPane.showConfirmDialog(this, panel, "✏️ Editar Produto", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    produto.setNome(nomeField.getText().trim());
                    produto.setPreco(Double.parseDouble(precoField.getText()));
                    produto.setQuantidade(Integer.parseInt(quantidadeField.getText()));
                    produto.setCategoria((String) categoriaCombo.getSelectedItem());
                    listarProdutos();
                    JOptionPane.showMessageDialog(this, "✅ Produto atualizado!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "❌ Erro nos dados!");
                }
            }
        }
    }
    
    
    private void adicionarProduto() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();
        JTextField quantidadeField = new JTextField();
        JComboBox<String> categoriaCombo = new JComboBox<>(Constants.CATEGORIAS);
        
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Preço (KZ):"));
        panel.add(precoField);
        panel.add(new JLabel("Quantidade:"));
        panel.add(quantidadeField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaCombo);
        
        if (JOptionPane.showConfirmDialog(this, panel, "➕ Adicionar Produto", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText().trim();
                double preco = Double.parseDouble(precoField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());
                String categoria = (String) categoriaCombo.getSelectedItem();
                
                Produto produto = new Produto(nextProdutoId++, nome, preco, quantidade, categoria);
                produtos.add(produto);
                listarProdutos();
                JOptionPane.showMessageDialog(this, "✅ Produto adicionado!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Erro nos dados!");
            }
        }
    }
    
    
    private void atualizarEstatisticas() {
        int totalProdutos = produtos.size();
        int totalEstoque = produtos.stream().mapToInt(Produto::getQuantidade).sum();
        double valorTotal = produtos.stream().mapToDouble(p -> p.getPreco() * p.getQuantidade()).sum();
        
        statusLabel.setText(String.format("📊 Produtos: %d | 📦 Estoque: %d | 💰 Valor: %,.0f KZ | 👤 Usuário: %s (%s)", 
                           totalProdutos, totalEstoque, valorTotal, usuarioLogado, tipoUsuario));
    }
    
    private void mostrarRelatorioVendas() {
        if (vendas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "📊 Nenhuma venda realizada!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "📈 Relatório de Vendas", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(1000, 600);
        
        DefaultTableModel relatorioModel = new DefaultTableModel(new String[]{"ID", "Cliente", "Vendedor", "Data", "Itens", "Total"}, 0);
        JTable relatorioTable = new JTable(relatorioModel);
        JScrollPane scrollPane = new JScrollPane(relatorioTable);
        
        double totalGeral = 0;
        for (Venda v : vendas) {
            StringBuilder itens = new StringBuilder();
            for (ItemVenda item : v.getItens()) {
                itens.append(item.getProduto().getNome());
                if (!item.getVariedade().equals("Padrão")) itens.append("(").append(item.getVariedade()).append(")");
                itens.append(" x").append(item.getQuantidade()).append(", ");
            }
            Object[] row = {v.getId(), v.getCliente(), v.getVendedor(), v.getData().toString(), 
                           itens.toString(), String.format("%,.0f KZ", v.getTotal())};
            relatorioModel.addRow(row);
            totalGeral += v.getTotal();
        }
        
        JLabel totalLabel = new JLabel(String.format("💰 TOTAL GERAL: %,.0f KZ | 📝 TOTAL DE VENDAS: %d", totalGeral, vendas.size()), SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Constants.COR_SECUNDARIA);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(totalLabel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void mostrarRelatorioProdutos() {
        listarProdutos();
        JOptionPane.showMessageDialog(this, "📊 Relatório de produtos atualizado!");
    }
    
    private void atualizarEstoque() {
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Sem produtos cadastrados!");
            return;
        }
        
        JDialog dialog = new JDialog(this, "📦 Atualizar Estoque", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 500);
        
        DefaultTableModel estoqueModel = new DefaultTableModel(new String[]{"ID", "Produto", "Quantidade Atual", "Nova Quantidade"}, 0);
        JTable estoqueTable = new JTable(estoqueModel);
        JScrollPane scrollPane = new JScrollPane(estoqueTable);
        
        for (Produto p : produtos) {
            estoqueModel.addRow(new Object[]{p.getId(), p.getNome(), p.getQuantidade(), ""});
        }
        
        JButton btnSalvar = criarBotao("💾 Salvar Alterações", Constants.COR_SUCESSO, 40, 200);
        btnSalvar.addActionListener(e -> {
            for (int i = 0; i < estoqueModel.getRowCount(); i++) {
                String novaQtd = (String) estoqueModel.getValueAt(i, 3);
                if (novaQtd != null && !novaQtd.trim().isEmpty()) {
                    try {
                        int id = (int) estoqueModel.getValueAt(i, 0);
                        int qtd = Integer.parseInt(novaQtd);
                        produtos.stream().filter(p -> p.getId() == id).findFirst().ifPresent(p -> p.setQuantidade(qtd));
                    } catch (NumberFormatException ex) {}
                }
            }
            listarProdutos();
            JOptionPane.showMessageDialog(dialog, "✅ Estoque atualizado com sucesso!");
            dialog.dispose();
        });
        
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(btnSalvar, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void mostrarBaixoEstoque() {
        List<Produto> baixoEstoque = produtos.stream().filter(p -> p.getQuantidade() <= 5).toList();
        if (baixoEstoque.isEmpty()) {
            JOptionPane.showMessageDialog(this, "✅ Todos os produtos têm estoque adequado!");
            return;
        }
        
        tableModel.setRowCount(0);
        for (Produto p : baixoEstoque) {
            String variedadesStr = !p.getVariedades().isEmpty() ? p.getVariedades().toString() : "Padrão";
            Object[] row = {p.getId(), p.getNome(), variedadesStr, p.getCategoria(), 
                           String.format("%,.0f", p.getPreco()), p.getQuantidade(),
                           String.format("%,.0f", p.getPreco() * p.getQuantidade())};
            tableModel.addRow(row);
        }
        JOptionPane.showMessageDialog(this, "⚠️ ATENÇÃO: " + baixoEstoque.size() + " produtos com baixo estoque (≤ 5 unidades)!");
    }
    
    private void mostrarPromocoes() {
        String promocoes = String.format("""
            🎉 PROMOÇÕES %s 🎉
            
            🥪 Coxinha + Refrigerante: 800 KZ (Economize 150 KZ!)
            🥤 Compre 2 Sucos e ganhe 1 Brigadeiro Grátis!
            ☕ Café + Pão de Queijo: 600 KZ
            🎂 Bolo de Chocolate: 10%% OFF à vista!
            📦 Leve 3 Salgados e pague 2!
            
            📞 Informações: %s
            ✉️ Email: %s
            
            🏫 Equipe %s - Qualidade e Excelência!
            """, Constants.NOME_EQUIPE, Constants.TELEFONE_CONTATO, Constants.EMAIL_CONTATO, Constants.NOME_EQUIPE);
        JOptionPane.showMessageDialog(this, promocoes, "🎯 PROMOÇÕES ESPECIAIS", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarCardapioDigital() {
        JDialog dialog = new JDialog(this, "📱 Cardápio Digital - " + Constants.NOME_EQUIPE, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(700, 600);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        for (String categoria : Constants.CATEGORIAS) {
            JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            panel.setBackground(Color.WHITE);
            
            for (Produto p : produtos) {
                if (p.getCategoria().equals(categoria)) {
                    JPanel itemPanel = new JPanel(new BorderLayout());
                    itemPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                    itemPanel.setBackground(Color.black);
                    
                    JLabel nomeLabel = new JLabel("🍽️ " + p.getNome());
                    nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    
                    JLabel precoLabel = new JLabel(String.format("💰 %,.0f KZ", p.getPreco()));
                    precoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    precoLabel.setForeground(Constants.COR_SECUNDARIA);
                    
                    JLabel variedadesLabel = new JLabel("🎨 " + (p.getVariedades().isEmpty() ? "Padrão" : String.join(", ", p.getVariedades().keySet())));
                    variedadesLabel.setFont(new Font("Arial", Font.PLAIN, 10));
                    variedadesLabel.setForeground(Color.GRAY);
                    
                    itemPanel.add(nomeLabel, BorderLayout.NORTH);
                    itemPanel.add(precoLabel, BorderLayout.CENTER);
                    itemPanel.add(variedadesLabel, BorderLayout.SOUTH);
                    
                    panel.add(itemPanel);
                }
            }
            tabbedPane.addTab(categoria, new JScrollPane(panel));
        }
        
        JLabel footerLabel = new JLabel("🏫 " + Constants.NOME_EQUIPE + " - Qualidade que conecta! 🏫", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        footerLabel.setForeground(Constants.COR_PRIMARIA);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        dialog.add(tabbedPane, BorderLayout.CENTER);
        dialog.add(footerLabel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void mostrarContato() {
        JOptionPane.showMessageDialog(this,
            "📞 INFORMAÇÕES DE CONTATO 📞\n\n" +
            "✉️ Email: " + Constants.EMAIL_CONTATO + "\n" +
            "📱 Telefone: " + Constants.TELEFONE_CONTATO + "\n" +
            "🏫 Equipe: " + Constants.NOME_EQUIPE + "\n\n" +
            "📍 Horário de Atendimento:\n" +
            "Segunda a Sexta: 08:00 - 18:00\n" +
            "Sábado: 08:00 - 12:00\n\n" +
            "💬 Disponíveis para suporte 24/7 via email!",
            "📞 Contato", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void confirmarSair() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            String.format("Deseja sair do sistema?\n\n📊 Resumo:\n• Vendas realizadas: %d\n• Usuário: %s\n\nVolte sempre! 🏪\n\n%s - Qualidade que conecta!", 
                vendas.size(), usuarioLogado, Constants.NOME_EQUIPE),
            "Sair", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (timer != null) timer.stop();
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CantinaSistema();
        });
    }
}