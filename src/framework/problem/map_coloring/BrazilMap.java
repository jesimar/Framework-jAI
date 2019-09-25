/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.map_coloring;

/**
 *
 * @author jesimar
 */
public class BrazilMap {
    
    public static final int MAX_LEVEL = 27;
    public int level;
    public static int cost;
    
    //Variáveis: RS SC PR SP RJ ES MG MS MT GO DF BA SE AL PE PB RN CE PI MA TO PA AP AM RR RO AC
    //Indices: 
    //   0     1     2     3     4     5     6     7
    //   8     9     10    11    12    13    14    15
    //   16    17    18    19    20    21    22    23
    //   24    25    26
    private final String[] variable = new String[]
    {
        "RS", "SC", "PR", "SP", "RJ", "ES", "MG", "MS", 
        "MT", "GO", "DF", "BA", "SE", "AL", "PE", "PB", 
        "RN", "CE", "PI", "MA", "TO", "PA", "AP", "AM", 
        "RR", "RO", "AC"
    };     
    
    //Domínio: D_i = {vermelho,verde,azul}
    private final String[] domain = new String[]{"vermelho", "verde", "azul", "amarelo"};
    
    //Restrições: regiões adjacentes devem possuir cores diferentes
    
    private final int AMOUNT_STATES = 27;
    private final State states[] = new State[AMOUNT_STATES];
    
    public BrazilMap(){
        for (int i = 0; i < states.length; i++){
            states[i] = new State(variable[i]);
        }        
        defineAdjacents();
    }
    
    public BrazilMap(BrazilMap australia){
        for (int i = 0; i < states.length; i++){
            states[i] = new State(australia.states[i].getName(), australia.states[i].getColor());
        }
        defineAdjacents();
    }
    
    private void defineAdjacents(){
        //RS
        states[0].addAdjacent(states[1]);
        
        //SC
        states[1].addAdjacent(states[0]);
        states[1].addAdjacent(states[2]);
        
        //PR
        states[2].addAdjacent(states[1]);
        states[2].addAdjacent(states[3]);
        states[2].addAdjacent(states[7]);
            
        //SP
        states[3].addAdjacent(states[2]);
        states[3].addAdjacent(states[4]);
        states[3].addAdjacent(states[6]);
        states[3].addAdjacent(states[7]);
        
        //RJ
        states[4].addAdjacent(states[3]);
        states[4].addAdjacent(states[5]);
        states[4].addAdjacent(states[6]);
        
        //ES
        states[5].addAdjacent(states[4]);
        states[5].addAdjacent(states[6]);
        states[5].addAdjacent(states[11]);
        
        //MG
        states[6].addAdjacent(states[3]);
        states[6].addAdjacent(states[4]);
        states[6].addAdjacent(states[5]);
        states[6].addAdjacent(states[7]);
        states[6].addAdjacent(states[9]);
        states[6].addAdjacent(states[10]);
        
        //MS
        states[7].addAdjacent(states[2]);
        states[7].addAdjacent(states[3]);
        states[7].addAdjacent(states[6]);
        states[7].addAdjacent(states[8]);
        states[7].addAdjacent(states[9]);
        
        //MT
        states[8].addAdjacent(states[7]);
        states[8].addAdjacent(states[9]);
        states[8].addAdjacent(states[20]);
        states[8].addAdjacent(states[21]);
        states[8].addAdjacent(states[23]);
        states[8].addAdjacent(states[25]);
        
        //GO
        states[9].addAdjacent(states[6]);
        states[9].addAdjacent(states[7]);
        states[9].addAdjacent(states[8]);
        states[9].addAdjacent(states[10]);
        states[9].addAdjacent(states[11]);
        states[9].addAdjacent(states[20]);
        
        //DF
        states[10].addAdjacent(states[6]);
        states[10].addAdjacent(states[9]);
        
        //BA
        states[11].addAdjacent(states[5]);
        states[11].addAdjacent(states[6]);
        states[11].addAdjacent(states[9]);
        states[11].addAdjacent(states[12]);
        states[11].addAdjacent(states[13]);
        states[11].addAdjacent(states[14]);
        states[11].addAdjacent(states[18]);
        states[11].addAdjacent(states[20]);
        
        //SE
        states[12].addAdjacent(states[11]);
        states[12].addAdjacent(states[13]);
        
        //AL
        states[13].addAdjacent(states[11]);
        states[13].addAdjacent(states[12]);
        states[13].addAdjacent(states[14]);
        
        //PE
        states[14].addAdjacent(states[11]);
        states[14].addAdjacent(states[13]);
        states[14].addAdjacent(states[15]);
        states[14].addAdjacent(states[17]);
        states[14].addAdjacent(states[18]);
        
        //PB
        states[15].addAdjacent(states[14]);
        states[15].addAdjacent(states[16]);
        states[15].addAdjacent(states[17]);
        
        //RN
        states[16].addAdjacent(states[15]);
        states[16].addAdjacent(states[17]);
        
        //CE
        states[17].addAdjacent(states[14]);
        states[17].addAdjacent(states[15]);
        states[17].addAdjacent(states[16]);
        states[17].addAdjacent(states[18]);
        
        //PI
        states[18].addAdjacent(states[11]);
        states[18].addAdjacent(states[14]);
        states[18].addAdjacent(states[17]);
        states[18].addAdjacent(states[19]);
        
        //MA
        states[19].addAdjacent(states[18]);
        states[19].addAdjacent(states[20]);
        states[19].addAdjacent(states[21]);
        
        //TO
        states[20].addAdjacent(states[8]);
        states[20].addAdjacent(states[9]);
        states[20].addAdjacent(states[11]);
        states[20].addAdjacent(states[19]);
        states[20].addAdjacent(states[21]);
        
        //PA
        states[21].addAdjacent(states[8]);
        states[21].addAdjacent(states[19]);
        states[21].addAdjacent(states[20]);
        states[21].addAdjacent(states[22]);
        states[21].addAdjacent(states[23]);
        states[21].addAdjacent(states[24]);
        
        //AP
        states[22].addAdjacent(states[21]);
        
        //AM
        states[23].addAdjacent(states[8]);
        states[23].addAdjacent(states[21]);
        states[23].addAdjacent(states[24]);
        states[23].addAdjacent(states[25]);
        states[23].addAdjacent(states[26]);
        
        //RR
        states[24].addAdjacent(states[23]);
        
        //RO
        states[25].addAdjacent(states[8]);
        states[25].addAdjacent(states[23]);
        
        //AC
        states[26].addAdjacent(states[23]);
    }

    public State[] getStates() {
        return states;
    }

    public String[] getDomain() {
        return domain;
    }        
    
    public void action(int index, String color){
        states[index].setColor(color);
    }
    
    public void printState(){
        for (State state : states){
            System.out.println("State: " + state.getName() + " --> " + state.getColor());
        }        
    }
}
