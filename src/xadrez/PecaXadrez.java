package xadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	private int contandoMovimento;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContandoMovimento() {
		return contandoMovimento;
	}
	
	protected void aumentarContagemMovimento() {
		contandoMovimento++;
	}
	
	protected void diminuirContagemMovimento() {
		contandoMovimento--;
	}

	public PosicaoXadrez getPecaXadrez() {
		return PosicaoXadrez.daPosicao(posicao); 
	}
	
	protected boolean existePecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.cor != cor;
	}
	
}
