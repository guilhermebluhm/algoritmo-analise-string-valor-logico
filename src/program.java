public class program {

    public static void main(String[] args) {

        //bi_cd = se...somente se
        //cnd = se...entao
        //and
        //or
        //for cnd conector hardcoded flags to valorLogico1 is necessary modify to change logic state
        //bi_cd implements only operators or / and - not and / or, but two or is valid too
        String expressao = "(A + B) cnd (B - A)";

        boolean expressaoValida = false;
        boolean operacaoBI_CND = false;
        boolean operacaoCND = false;
        boolean operacaoAND = false;
        boolean operacaoOR = false;
        boolean operacaoOR_interna = false;
        boolean operacaoAND_interna = false;
        boolean valorLogico1 = false;
        boolean valorLogico2 = false;

        String v[] = new String[expressao.length()];
        for(int i = 0 ; i < expressao.length() ; i+=1){
            v = expressao.split(" ");
        }

        int aux1 = 0;
        int aux2 = 0;

        for(int i = 0 ; i < v.length ; i+=1){
            if(v[i].contains("(")){
                //System.out.println("encontrei abrindo");
                aux1+=1;
                if(aux1 == 2){
                    for(int j = v.length - 1 ; j >= 0 ; j-=1){
                        if(v[j].contains(")")){
                            //System.out.println(v[j]);
                            aux2+=1;
                        }
                    }
                }
            }
        }
        if(aux1 == 2 && aux2 == 2){
            expressaoValida = true;
        }
        if(expressaoValida == true){

            int aux3 = 0;
            int aux4 = 0;

            if(v[1].contains("-") && v[5].contains("-")){
                operacaoOR_interna = true;
                //System.out.println("encontrado apenas OR");
            }
            if(v[1].contains("-") && v[5].contains("+")
                    || v[1].contains("+") && v[5].contains("-")){
                operacaoOR_interna = true;
                operacaoAND_interna = true;
                //System.out.println("encontrado AND e OR");
            }

            System.out.println("sim e uma expressao valida...vamos continuar");
            for(int i = 0 ; i < v.length ; i+=1){
                if(v[i].contains("a".toUpperCase())){
                    aux3+=1;
                    if(aux3 == 2){
                        valorLogico1 = true;
                        //System.out.println("valores logicos de A - OK");
                    }
                }
                if(v[i].contains("b".toUpperCase())){
                    aux4+=1;
                    if(aux4 == 2){
                        valorLogico2 = false;
                        //System.out.println("valores logicos de B - OK");
                    }
                }
                if(v[3].contains("and")){
                    operacaoAND = true;
                }
                if(v[3].contains("or")){
                    operacaoOR = true;
                }
                if(v[3].contains("cnd")){
                    operacaoCND = true;
                }
                if(v[3].contains("bi_cd")){
                    operacaoBI_CND = true;
                }
            }
            if(aux3 != 2 || aux4 != 2){
                System.out.println("nao tem os valores logicos minimos impossivel continuar");
                return;
            }
            else{
                System.out.println("vamos continuar...");
                if(operacaoAND == false &&
                        operacaoOR == false &&
                        operacaoCND == false &&
                        operacaoBI_CND == false){
                    System.out.println("operacao nao identificada");
                    return;
                }
                if(operacaoAND){
                    System.out.println("Realizando uma operacao tipo AND");
                    if(operacaoOR_interna && !operacaoAND_interna){
                        System.out.println("e uma expressao com apenas OR");
                        boolean r = (valorLogico1 || valorLogico2) && (valorLogico1 || valorLogico2);
                        System.out.println("RESULTADO LOGICO: " + r);
                    }
                    if(operacaoOR_interna && operacaoAND_interna){
                        if(v[1].contains("-") && v[5].contains("+")){
                            System.out.println("e uma expressao com OR e AND");
                            boolean r = (valorLogico1 || valorLogico2) && (valorLogico1 && valorLogico2);
                            System.out.println("RESULTADO LOGICO: " + r);
                        }
                        if(v[1].contains("+") && v[5].contains("-")){
                            System.out.println("e uma expressao com AND e OR");
                            boolean r = (valorLogico1 && valorLogico2) && (valorLogico1 || valorLogico2);
                            System.out.println("RESULTADO LOGICO: " + r);
                        }
                    }
                }
                else if(operacaoOR){
                    System.out.println("Realizando uma operacao tipo OR");
                    if(operacaoOR_interna && !operacaoAND_interna){
                        System.out.println("e uma expressao com apenas OR");
                        boolean r = (valorLogico1 || valorLogico2) || (valorLogico1 || valorLogico2);
                        System.out.println("RESULTADO LOGICO: " + r);
                    }
                    if(operacaoOR_interna && operacaoAND_interna){
                        if(v[1].contains("-") && v[5].contains("+")){
                            System.out.println("e uma expressao com OR e AND");
                            boolean r = (valorLogico1 || valorLogico2) || (valorLogico1 && valorLogico2);
                            System.out.println("RESULTADO LOGICO: " + r);
                        }
                        if(v[1].contains("+") && v[5].contains("-")){
                            System.out.println("e uma expressao com AND e OR");
                            boolean r = (valorLogico1 && valorLogico2) || (valorLogico1 || valorLogico2);
                            System.out.println("RESULTADO LOGICO: " + r);
                        }
                    }
                }
                else if(operacaoCND){
                    System.out.println("Realizando uma operacao condicional");
                    if(operacaoOR_interna && !operacaoAND_interna){
                        System.out.println("e uma expressao com apenas OR");
                        boolean r = (valorLogico1 || valorLogico2) || (valorLogico1 || valorLogico2);
                        System.out.println("RESULTADO LOGICO: " + r);
                    }
                    if(operacaoOR_interna && operacaoAND_interna){
                        if(v[1].contains("-") && v[5].contains("+")){
                            System.out.println("e uma expressao com OR e AND");
                            if(valorLogico1 && valorLogico2 == false){
                                Boolean r = false;
                                System.out.println("RESULTADO LOGICO: " + r);
                                return;
                            }
                            boolean r = (valorLogico1 || valorLogico2) || (valorLogico1 && valorLogico2);
                            if(r == false){
                                System.out.println("RESULTADO LOGICO: " + !r);
                                return;
                            }
                            System.out.println("RESULTADO LOGICO: " + r);
                        }
                        if(v[1].contains("+") && v[5].contains("-")){
                            System.out.println("e uma expressao com AND e OR");
                            boolean r = (valorLogico1 && valorLogico2) || (valorLogico1 || valorLogico2);
                            System.out.println("RESULTADO LOGICO: " + r);
                        }
                    }
                }
                else if(operacaoBI_CND){
                    System.out.println("Realizando uma operacao bi-condicional");
                    if(operacaoOR_interna && !operacaoAND_interna){
                        System.out.println("e uma expressao com apenas OR");
                        boolean r = (valorLogico1 || valorLogico2) || (valorLogico1 || valorLogico2);
                        if(r){
                            if(valorLogico1 == true){
                                System.out.println("RESULTADO LOGICO: " + r);
                                return;
                            }
                        }
                        if(valorLogico1 == true && valorLogico2 == true ||
                                valorLogico1 == false && valorLogico2 == false){
                            System.out.println("RESULTADO LOGICO: True");
                            return;
                        }
                    }
                    if(operacaoOR_interna && operacaoAND_interna){
                        if(v[1].contains("-") && v[5].contains("+")){
                            System.out.println("e uma expressao com OR e AND");
                            if(valorLogico1 && valorLogico2 == false){
                                Boolean r = false;
                                System.out.println("RESULTADO LOGICO: " + r);
                                return;
                            }
                            if(valorLogico1 && valorLogico2 == true){
                                Boolean r = true;
                                System.out.println("RESULTADO LOGICO: " + r);
                                return;
                            }
                        }
                    }
                }
            }
        }
        else{
            System.out.println("impossivel continuar, nao e uma expressao valida");
        }
    }
}