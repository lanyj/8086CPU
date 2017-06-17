CODE SEGMENT
    ASSUME CS:CODE
START:

    MOV AX,12
    MOV CX,12
    CALL FARB
    ADD AX,CX

    MOV DX,AX

    HLT

    SHOW PROC
    	PUSH DX
    	PUSH AX
    	MOV AX,DX
    	
        MOV DX,0FFFFH
        OUT DX,AL
        
        POP AX
        POP DX
        RET
    ENDP

    FARB PROC
    	PUSH AX
    	PUSH BX
    	PUSH CX
    	PUSH DX
    	
    	MOV CX,8
    	
    	MOV AX,1
    	MOV BX,1
    	PUSH BX
    	PUSH AX
    	
    	MOV DX,AX
    	CALL SHOW
    REP_S:
    	POP AX
    	POP BX

    	MOV DX,AX
    	CALL SHOW

        ADD AX,BX
        
        PUSH DX
        PUSH AX
    	LOOP REP_S
    	
    	POP CX
    	POP CX
    	
    	POP DX
    	POP CX
    	POP BX
    	POP AX
	RET
    ENDP
    
CODE ENDS
END START