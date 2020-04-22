package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		//CANTO 1
		p.definirValores(posicao.getLinha()-2, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
				mat[p.getLinha()][p.getColuna()] = true;	
		}
		//CANTO 2
		p.definirValores(posicao.getLinha()-1, posicao.getColuna()-2);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//CANTO 3
		p.definirValores(posicao.getLinha()+2, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			p.definirValores(posicao.getLinha()+2, posicao.getColuna()-1);
			mat[p.getLinha()][p.getColuna()] = true;
	
		}
		//CANTO 4
		p.definirValores(posicao.getLinha()+1, posicao.getColuna()-2);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//CANTO 5
		p.definirValores(posicao.getLinha()-2, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//CANTO 6
		p.definirValores(posicao.getLinha()-1, posicao.getColuna()+2);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//CANTO 7
		p.definirValores(posicao.getLinha()+1, posicao.getColuna()+2);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//CANTO 8
		p.definirValores(posicao.getLinha()+2, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "C";
	}

}
