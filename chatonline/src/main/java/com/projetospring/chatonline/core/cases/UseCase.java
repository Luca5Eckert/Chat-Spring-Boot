package com.projetospring.chatonline.core.cases;

public interface UseCase<INPUT, OUTPUT>{

    public OUTPUT execute(INPUT input);

}
