package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class NewspapersEntityKey extends EntityKey implements INewspapersEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long paperId;
    public NewspapersEntityKey() {    }    public NewspapersEntityKey(Long paperId) {        super(new Object[] { paperId });
        this.paperId = paperId;
    }    public Long getPaperId() {        return paperId;
    }}
