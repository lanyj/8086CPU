CODE SEGMENT
    ASSUME CS:CODE
START:

    MOV AX,12
    MOV CX,12
    CALL FARB
    ADD AX,CX

    MOV DX,AX

    HLT

    FARB PROC
    	PUSH AX
    	PUSH BX
    	PUSH CX
    	PUSH DX
    	
    	MOV CX,4
    	
    	MOV AX,1
    	MOV BX,1
    	PUSH BX
    	PUSH AX
    	
    	MOV DX,AX
    REP_S:
    	POP AX
    	POP BX

    	MOV DX,AX

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