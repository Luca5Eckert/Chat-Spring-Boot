package com.projetospring.chatonline.core.command;

import com.projetospring.chatonline.core.enums.TypeEdit;

public interface EditCommand<T, V> {

    public void execute(T object,V v);

    public TypeEdit getType();

}
