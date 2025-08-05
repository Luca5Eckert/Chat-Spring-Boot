package com.projetospring.chatonline.core.command;

import com.projetospring.chatonline.core.enums.TypeEdit;

public interface EditCommand<T> {

    public void execute(T object);

    public TypeEdit getType();

}
