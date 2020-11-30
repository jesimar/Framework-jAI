package framework.main;

import framework.method.eda.UMDA;
import framework.method.eda.UMDAc;
import framework.method.eda.UMDAcBimodal;
import framework.method.eda.cGA;
import framework.method.ga.AGsr;
import framework.method.ga.AGsrMultiCros;
import framework.method.ga.DE;
import framework.method.ga.sGA;
import framework.method.ga.PSO;
import framework.method.heuristics.GerarETestar;
import framework.method.heuristics.Grasp;
import framework.problem.cannibals.SolveCannibals;
import framework.problem.kmeans.Kmeans;
import framework.problem.map_coloring.SolveMap;
import framework.problem.nqueens.SolveNQueen;

/**
 *
 * @author jesimar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        SolveNQueen solveQueen = new SolveNQueen();
        
//        SolveCannibals solveCannibals = new SolveCannibals();
        
//        SolveMap solveMap = new SolveMap();
        
//        Kmeans kmeans = new Kmeans(100, 2);
//        kmeans.initData();
//        kmeans.exec();
//        kmeans.printData();
        
        //Avaliado
//        GerarETestar gerar = new GerarETestar();
//        gerar.exec();
        
        //Avaliado
//        Grasp grasp = new Grasp(); 
//        grasp.exec();
        
        //Avaliado
//        AGsr agsr = new AGsr();
//        agsr.exec();
        
        //Avaliado
        sGA sGA = new sGA();
        sGA.exec();
        
        //Avaliado
//        AGsrMultiCros agsrMC = new AGsrMultiCros();
//        agsrMC.exec();
        
        //Avaliado
//        DE de = new DE();
//        de.exec();

//        cGA cga = new cGA();
//        cga.exec();      
        
//        UMDA umda = new UMDA();
//        umda.exec();
        
//        UMDAc umdac = new UMDAc();
//        umdac.exec();
        
//        UMDAcBimodal umdacBimodal = new UMDAcBimodal();
//        umdacBimodal.exec();
        
        //Não esta muito bom
//        MIMICcG mimic = new MIMICcG();
//        mimic.exec();

        //Não terminado
//        PSO pso = new PSO();
//        pso.exec();

        //Não terminado
//        EcGA ecga = new EcGA();
//        ecga.exec();          
    }
}
