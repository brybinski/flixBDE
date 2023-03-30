package com.bde.flix.model.userman;

import com.bde.flix.model.History;

public abstract class Account {
    private long id;
    private String email;
    private String hash;
    private boolean availSub;
    private History[] hist;
}
