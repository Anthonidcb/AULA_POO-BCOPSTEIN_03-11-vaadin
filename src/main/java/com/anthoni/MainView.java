package com.anthoni;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private TextField nomeTf;
    private TextField pesoTf;
    private TextField alturaTf;
    private final Grid<PessoaEstat> estatisticasGrid;
    private List<PessoaEstat> estatisticas;
    
    public MainView() {
        nomeTf = new TextField("Nome");
        pesoTf = new TextField("Peso");
        alturaTf = new TextField("Altura");
        estatisticasGrid = new Grid<>(PessoaEstat.class);
        estatisticas = new LinkedList<>();
        //estatisticas.add(new PessoaEstat("Ze",85,182,"Bom","Estatura media"));
        estatisticasGrid.setItems(estatisticas);
        estatisticasGrid.setColumns("nome", "peso", "altura", "classPeso", "classAltura");

        setSpacing(true);
        setPadding(true);

        add(new H2("Estatisticas de saude"));

        FormLayout formularioLT = new FormLayout(nomeTf, pesoTf, alturaTf);

        Button calcPesoButton = new Button("Estatisticas de Saude");
        calcPesoButton.addClickListener(e->exibirEstatisticasDeSaude(extraiDadosPessoa()));
        
        HorizontalLayout botaoLayout = new HorizontalLayout(calcPesoButton);
        botaoLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        botaoLayout.setWidthFull();
        
        add(formularioLT);
        add(botaoLayout);
        add(estatisticasGrid);
    }

    private Pessoa extraiDadosPessoa(){
        String nome = nomeTf.getValue();
        int peso = Integer.parseInt(pesoTf.getValue());
        int altura = Integer.parseInt(alturaTf.getValue());
        Pessoa p = new Pessoa(nome,peso,altura);
        return p;
    }

    private void exibirEstatisticasDeSaude(Pessoa p){
        String classPeso = EstatisticasSaude.classificaPeso(p);
        String classAltura = EstatisticasSaude.classificaAltura(p);
        
        // Criar o diálogo
        Dialog dialogo = new Dialog();
        dialogo.setWidth("400px");
        
        // Título do diálogo
        H3 titulo = new H3("Estatísticas de Saúde");
        
        // Layout vertical para o conteúdo
        VerticalLayout conteudo = new VerticalLayout();
        conteudo.setSpacing(false);
        conteudo.setPadding(false);
        
        // Adicionar as estatísticas
        Paragraph pesoInfo = new Paragraph("Classificação do Peso: " + classPeso);
        Paragraph alturaInfo = new Paragraph("Classificação da altura: " + classAltura);
        
        conteudo.add(pesoInfo);
        conteudo.add(alturaInfo);

        // Insere informaçoes no grid
        PessoaEstat pest = new PessoaEstat(p.getNome(),p.getPeso(),p.getAltura(),classPeso,classAltura);
        estatisticas.add(pest);
        estatisticasGrid.getDataProvider().refreshAll();
        
        // Botão para fechar o diálogo
        Button fecharButton = new Button("Fechar", e -> dialogo.close());
        fecharButton.getStyle().set("margin-top", "10px");
        
        HorizontalLayout botoesLayout = new HorizontalLayout(fecharButton);
        botoesLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        botoesLayout.setWidthFull();
        
        // Adicionar componentes ao diálogo
        dialogo.add(titulo, conteudo, botoesLayout);
        
        // Abrir o diálogo
        dialogo.open();
    }
}
