import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/
 
public class  ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
	String pieceName;
	Boolean agentwins = false;
	Boolean white2Move = false;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	int landingX;
	int landingY;
	JPanel panels;
	JLabel pieces;
	Boolean progression=false;
	AIAgent agent = new AIAgent();

 
    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);
 
        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }
 
        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){			
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){			
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);	        
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);		
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	public Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	public String getPieceName(int x, int y) {
		String returnedPieceName="";
		x=(x* 75);
		y=(y* 75);
		Component c2 = chessBoard.findComponentAt(x, y);
		if(c2 instanceof JLabel){
			JLabel awaitingPiece = (JLabel)c2;
			 returnedPieceName = awaitingPiece.getIcon().toString();
			return returnedPieceName;
		}
		else {
			return returnedPieceName;
		}

	}
	public Boolean checkSurroundingSquares(Square s){
		Boolean possible = false;
		int x = s.getXC()*75;
		int y = s.getYC()*75;
		if(!((getPieceName((x+75), y).contains("BlackKing"))||(getPieceName((x-75), y).contains("BlackKing"))||(getPieceName(x,(y+75)).contains("BlackKing"))||(getPieceName((x), (y-75)).contains("BlackKing"))||(getPieceName((x+75),(y+75)).contains("BlackKing"))||(getPieceName((x-75),(y+75)).contains("BlackKing"))||(getPieceName((x+75),(y-75)).contains("BlackKing"))||(getPieceName((x-75), (y-75)).contains("BlackKing")))){
			possible = true;

		}
		return possible;
	}
	public Boolean checkSurroundingSquaresWhite(Square s){
		Boolean possible = false;
		int x = s.getXC()*75;
		int y = s.getYC()*75;
		if(((getPieceName((x+75), y).contains("WhiteKing"))||(getPieceName((x-75), y).contains("WhiteKing"))||(getPieceName(x,(y+75)).contains("WhiteKing"))||(getPieceName((x), (y-75)).contains("WhiteKing"))||(getPieceName((x+75),(y+75)).contains("WhiteKing"))||(getPieceName((x-75),(y+75)).contains("WhiteKing"))||(getPieceName((x+75),(y-75)).contains("WhiteKing"))||(getPieceName((x-75), (y-75)).contains("WhiteKing")))){
			possible = true;
		}
		return possible;
	}
	public int getPieceScore(int x, int y, String name){
		int	pieceScore = 0;
		if(piecePresent(x, y)) {

			if(name.contains("BlackPawn") && pieceScore < 2){
				//oponent = true;
				pieceScore = 1;
				System.out.println("Black Pawn found with a value of " + pieceScore);
			}
			if(name.contains("BlackKnight")&& pieceScore < 3){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 3;
				}
				System.out.println("Black Knight found with a value of " + pieceScore);
			}
			if(name.contains("BlackBishup")&& pieceScore < 3){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 3;
				}
				System.out.println("Black Bishop found with a value of " + pieceScore);
			}
			if(name.contains("BlackRook")&& pieceScore < 5){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 5;
				}
				System.out.println("Black Rook found with a value of " + pieceScore);
			}
			if(name.contains("BlackQueen")&& pieceScore < 9){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 9;
				}
				System.out.println("Black Queen found with a value of " + pieceScore);
			}
			if(name.contains("BlackKing")&& pieceScore < 100){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 999;
				}
				System.out.println("Black King found with a value of " + pieceScore);
			}
			if(name.isEmpty()){
				pieceScore = 0;
				System.out.println("No Piece Found | No Points");
			}

		}


		return pieceScore;
	}

	public int getBlackPieceScore(int x, int y, String name){
		int	pieceScore = 0;
		if(piecePresent(x, y)) {

			if(name.contains("WhitePawn") && pieceScore < 2){
				//oponent = true;
				pieceScore = 1;
				System.out.println("White Pawn found with a value of " + pieceScore);
			}
			if(name.contains("WhiteKnight")&& pieceScore < 3){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 3;
				}
				System.out.println("White Knight found with a value of " + pieceScore);
			}
			if(name.contains("WhiteBishup")&& pieceScore < 3){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 3;
				}
				System.out.println("White Bishop found with a value of " + pieceScore);
			}
			if(name.contains("WhiteRook")&& pieceScore < 5){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 5;
				}
				System.out.println("White Rook found with a value of " + pieceScore);
			}
			if(name.contains("WhiteQueen")&& pieceScore < 9){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 9;
				}
				System.out.println("White Queen found with a value of " + pieceScore);
			}
			if(name.contains("WhiteKing")&& pieceScore < 100){
				//oponent = true;
				if(piecePresent(x,y)){
					pieceScore = 999;
				}
				System.out.println("White King found with a value of " + pieceScore);
			}
			if(name.isEmpty()){
				pieceScore = 0;
				System.out.println("No Piece Found | No Points");
			}

		}


		return pieceScore;
	}
	
	/*
		This is a method to check if a piece is a Black piece.
	*/
	public Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();			
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false; 
		}		
		return oponent;
	}
	public Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	public void colorSquares(Stack squares) {
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
		while (!squares.empty()) {
			Square s = (Square) squares.pop();
			int location = s.getXC() + ((s.getYC()) * 8);
			JPanel panel = (JPanel) chessBoard.getComponent(location);
			panel.setBorder(greenBorder);
		}
	}
	public void resetBorders(){
		Border empty = BorderFactory.createEmptyBorder();
		for(int i=0;i < 64;i++){
			JPanel tmppanel = (JPanel)chessBoard.getComponent(i);
			tmppanel.setBorder(empty);
		}
	}

	/*
      The method printStack takes in a Stack of Moves and prints out all possible moves.
    */
	public void printStack(Stack input){
		Move m;
		Square s, l;
		while(!input.empty()){
			m = (Move)input.pop();
			s = (Square)m.getStart();
			l = (Square)m.getLanding();
			System.out.println("The possible move that was found is : ("+s.getXC()+" , "+s.getYC()+"), landing at ("+l.getXC()+" , "+l.getYC()+")");
		}
	}
	public void getLandingSquares(Stack found){
		Move tmp;
		Square landing;
		Stack squares = new Stack();
		while(!found.empty()){
			tmp = (Move)found.pop();
			landing = (Square)tmp.getLanding();
			squares.push(landing);
		}
		colorSquares(squares);
	}
	public Stack findWhitePieces() {
		Stack squares = new Stack();
		String icon;
		int x;
		int y;
		String pieceName;
		for (int i = 0; i < 600; i += 75) {
			for (int j = 0; j < 600; j += 75) {
				y = i / 75;
				x = j / 75;
				Component tmp = chessBoard.findComponentAt(j, i);
				if (tmp instanceof JLabel) {
					chessPiece = (JLabel) tmp;
					icon = chessPiece.getIcon().toString();
					pieceName = icon.substring(0, (icon.length() - 4));
					if (pieceName.contains("White")) {
						Square stmp = new Square(x, y, pieceName);
						squares.push(stmp);
					}
				}
			}
		}
		return squares;
	}

	public Stack findBlackPieces() {
		Stack squares = new Stack();
		String icon;
		int x;
		int y;
		String pieceName;
		for (int i = 0; i < 600; i += 75) {
			for (int j = 0; j < 600; j += 75) {
				y = i / 75;
				x = j / 75;
				Component tmp = chessBoard.findComponentAt(j, i);
				if (tmp instanceof JLabel) {
					chessPiece = (JLabel) tmp;
					icon = chessPiece.getIcon().toString();
					pieceName = icon.substring(0, (icon.length() - 4));
					if (pieceName.contains("Black")) {
						Square stmp = new Square(x, y, pieceName);
						squares.push(stmp);
					}
				}
			}
		}
		return squares;
	}
	public  Stack blackAttacks(){
		Stack black = findBlackPieces();
		Stack completeMoves = new Stack();
		Move tmp;
		Stack temporary = new Stack();
		while(!black.empty()){
			Square s = (Square)black.pop();
			String tmpString = s.getName();
			Stack tmpMoves = new Stack();

    /*
        We need to identify all the possible moves that can be made by the AI Opponent
    */
			if(tmpString.contains("Knight")){
				tmpMoves = getBlackKnightMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Bishop")){
				tmpMoves = getBlackBishopMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Pawn")){
				tmpMoves = getBlackPawnSquares(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Rook")){
				tmpMoves = getBlackRookMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Queen")){
				tmpMoves = getBlackQueenMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("King")){
				tmpMoves = getBlackKingSquares(s.getXC(), s.getYC(), s.getName());
			}

			while(!tmpMoves.empty()){
				tmp = (Move)tmpMoves.pop();
				completeMoves.push(tmp);
			}
		}
		return completeMoves;
	}

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have 
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) 
			return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);

        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
   
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }
     
 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before 
		putting the piece back on the board.
	*/

    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;
 
        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;
		landingX=(e.getX()/75);
		landingY=(e.getY()/75);

		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.
			
			So a Pawn is able to move two squares forward one its first go but only one square after that. 
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing 
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one 
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position. 
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for 
			demonstration purposes the Pawn here turns into a Queen.
		*/

		if(pieceName.equals("BlackPawn")){
			if(startY==6){
				if((startX == landingX)&&(((startY-landingY)==1)||(startY-landingY)==2)){
					if((startY-landingY)==2){
						if(!piecePresent(e.getX(), (e.getY())) && (!piecePresent(e.getX(), e.getY() + 75))){
							validMove = true;
							System.out.println(e.getX()+"  "+e.getY());
						}

					}
					else if((startY-landingY)==1){
						if(!piecePresent(e.getX(), (e.getY()))){
							validMove = true;
							if(startY==1){
								progression=true;
							}
						}
						else if(piecePresent(e.getX(), e.getY())&&(((startX-landingX)==1)||(startX-landingX)==-1)){
							if(checkBlackOponent(e.getX(),e.getY())){
								validMove=true;
								checkMate(e.getX(),e.getY());
								if(startY==1){
									progression=true;
								}
							}else {
								validMove = false;
							}
						}



					}


					else {
						validMove = false;
					}
				}
				else {
					validMove = false;
				}
			}

				else if((Math.abs(startX-landingX)==1)&&(((startY-landingY)==1))){
					if(piecePresent(e.getX(),e.getY())){
						if(checkBlackOponent(e.getX(),e.getY())){
							validMove=true;
							checkMate(e.getX(),e.getY());
							if(landingY==0){
								progression=true;
							}
						}else {
							validMove = false;
						}
					}
					else {
						validMove = false;
					}

				}
			else if((startY!=6)&&((startX==landingX)&&(((startY-landingY)==1)))){
				if(!piecePresent(e.getX(),e.getY())){
					validMove=true;
					if(landingY==0){
						progression=true;
					}
				}
				else {
					validMove=false;
				}
			}
			else{
				validMove=false;
			}



		}



		else if(pieceName.equals("WhitePawn")){
			if(startY == 1)
			{
				if((landingX == startX)&&(((landingY-startY)==1)||(landingY-startY)==2)){
					if((landingY-startY)==2){
						if(!piecePresent(e.getX(), (e.getY())) && (!piecePresent(e.getX(), e.getY() - 75))){
							validMove = true;
							System.out.println(e.getX()+"  "+e.getY());
						}
					}
					else if((landingY-startY)==1){
						if(!piecePresent(e.getX(), (e.getY()))){
							validMove = true;
							System.out.println(e.getX()+"  "+e.getY());
						}
					}


					else {
						validMove = false;
					}
				}
				else {
					validMove = false;
				}
			}

			else if((Math.abs(landingX-startX)==1)&&(((landingY-startY)==1))){
				if(piecePresent(e.getX(),e.getY())){
					if(checkWhiteOponent(e.getX(),e.getY())){
						validMove=true;
						if(landingY==0){
							progression=true;
						}
					}else {
						validMove = false;
					}
				}
				else {
					validMove = false;
				}

			}
			else if((startY!=1)&&((landingX==startX)&&(((landingY-startY)==1)))){
				if(!piecePresent(e.getX(),e.getY())){
					validMove=true;
					if(landingY==0){
						progression=true;
					}
				}
				else {
					validMove=false;
				}
			}
			else{
				validMove=false;
			}

		}
		else  if(pieceName.contains("Knight")){
			if(((landingX<0)||(landingX>7))||((landingY<0)||(landingY>7))){
				validMove=false;
			}
			else {
				if(((landingX==startX+1)&&(landingY==startY+2))||((landingX==startX-1)&&(landingY==startY+2))||((landingX==startX+2)&&(landingY==startY+1))||((landingX==startX-2)&&(landingY==startY+1))||((landingX==startX+1)&&(landingY==startY-2))||((landingX==startX-1)&&(landingY==startY-2))||((landingX==startX+2)&&(landingY==startY-1))||((landingX==startX-2)&&(landingY==startY-1))){
					if(piecePresent(e.getX(),e.getY())){
						if(pieceName.contains("White")){
							if(checkWhiteOponent(e.getX(),e.getY())){
								validMove=true;
							}
							else {
								validMove=false;
							}
						}

						else {
							if(checkBlackOponent(e.getX(),e.getY())){
								validMove=true;
							}
							else {
								validMove=false;
							}
						}

					}
					else{
						validMove=true;
					}

				}
				else {
					validMove=false;
				}
			}
		}
		else if(pieceName.contains("Bishup")){
			Boolean inTheWay=false;
			int distance=Math.abs(startX-landingX);
			if(((landingX<0)||((landingX>7))||((landingY<0))||(landingY>7))){
				validMove=false;
			}
			else {
				validMove=true;
				if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					}
					if(inTheWay){
						validMove=false;
					}
					else {
						if(piecePresent(e.getX(),e.getY())){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(),e.getY())){
									validMove=true;
								}
								else {
									validMove=false;
								}
							}
							else{
								if(checkBlackOponent(e.getX(),e.getY())){
									validMove=true;
								}
								else {
									validMove=false;
								}
							}
						}
						else {
							validMove=true;
						}
					}
				}
				else {
					validMove=false;
				}
			}
		}
		else if(pieceName.contains("Rook")){

			Boolean inTheWay=false;
			if(((landingX<0)||((landingX>7))||((landingY<0))||(landingY>7))){
				validMove=false;
			}
			else {
				if(((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)==0)||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY)!=0)))){
					if(Math.abs(startX-landingX)!=0){
						int xMovement=Math.abs(startX-landingX);
						if(startX-landingX>0){
							for(int i=0;i<xMovement;i++){
								if(piecePresent(initialX-(i*75),e.getY())){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
						else {
							for(int i=0;i<xMovement;i++){
								if(piecePresent(initialX+(i*75),e.getY())){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
					}
					else {
						int yMovement=Math.abs(startY-landingY);
						if(startY-landingY>0){
							for(int i=0;i<yMovement;i++){
								if(piecePresent(e.getX(),initialY-(i*75))){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
						else {
							for(int i=0;i<yMovement;i++){
								if(piecePresent(e.getX(),initialY+(i*75))){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
					}
					if(inTheWay){
						validMove=false;
					}
					else{
						if(piecePresent(e.getX(),e.getY())){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(),e.getY())){
									validMove=true;
								}
								else {
									validMove=false;
								}
							}
							else {
								if (checkBlackOponent(e.getX(),e.getY())){
									validMove=true;
								}
								else {
									validMove=false;
								}
							}
						}
						else {
							validMove=true;
						}
					}
				}
				else {
					validMove=false;
				}
			}
		}
		else if(pieceName.contains("Queen")){
			Boolean inTheWay=false;
			int distance=Math.abs(startX-landingX);
			if(((landingX<0)||((landingX>7))||((landingY<0))||(landingY>7))){
				validMove=false;
			}
			else {
				if((Math.abs(startX-landingX)==Math.abs(startY-landingY))||((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY)==0)||((Math.abs(startX-landingX)==0)&&(Math.abs(landingY)!=0)))){
					if ((startX - landingX < 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
								inTheWay = true;
							}
						}
					} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
						for (int i = 0; i < distance; i++) {
							if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
								inTheWay = true;
							}
						}
					}
					else if(Math.abs(startX-landingX)!=0){
						int xMovement=Math.abs(startX-landingX);
						if(startX-landingX>0){
							for(int i=0;i<xMovement;i++){
								if(piecePresent(initialX-(i*75),e.getY())){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
						else {
							for(int i=0;i<xMovement;i++){
								if(piecePresent(initialX+(i*75),e.getY())){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
					}
					else {
						int yMovement=Math.abs(startY-landingY);
						if(startY-landingY>0){
							for(int i=0;i<yMovement;i++){
								if(piecePresent(e.getX(),initialY-(i*75))){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
						else {
							for(int i=0;i<yMovement;i++){
								if(piecePresent(e.getX(),initialY+(i*75))){
									inTheWay=true;
									break;
								}
								else {
									inTheWay=false;
								}
							}
						}
					}
					if(inTheWay){
						validMove=false;
					}
					else{
						if(piecePresent(e.getX(),e.getY())){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(),e.getY())){
									validMove=true;
								}
								else {
									validMove=false;
								}
							}
							else {
								if (checkBlackOponent(e.getX(),e.getY())){
									validMove=true;
								}
								else {
									validMove=false;
								}
							}
						}
						else {
							validMove=true;
						}
					}

				}//move opps

				else {
					validMove=false;
				}
			}
		}
		else if(pieceName.contains("King")){
			Boolean inTheWay=false;
			int distanceX=Math.abs(startX-landingX);
			int distanceY=Math.abs(startY-landingY);
			int x = e.getX();
			int y = e.getY();
			Square s=new Square(x,y);
			if(((landingX<0)||((landingX>7))||((landingY<0))||(landingY>7))){
				validMove=false;
			}


			else {
				if(((distanceX <= 1) || (distanceX <= -1)) && ((distanceY <= -1) || (distanceY <= 1))){
					if ((landingX == startX)&&(landingY == startY)) {
						validMove = false;
					}
					else{
						if(!piecePresent(e.getX(), e.getY())){
							validMove = true;
						}
						else{
							System.out.println(landingX+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ y);
							if(checkSurroundingSquaresWhite(s)){
								System.out.println("One away from king!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
								validMove=false;
							}
							else if (pieceName.contains("White")) {
								if (checkWhiteOponent(e.getX(), e.getY())) {

									validMove = true;
								}
							}
							else if (pieceName.contains("Black")) {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								}
							}
							else {
								validMove = false;
							}
						}
					}
				}
			}

		}
		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png"; 
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);			
		}
		else{
			if (progression==true){
				int location=0+(e.getX()/75);
				if(c instanceof JLabel){
					Container parent= c.getParent();
					parent.remove(0);
					pieces=new JLabel(new ImageIcon("BlackQueen.png"));
					parent=(JPanel)chessBoard.getComponent(location);
					parent.add(pieces);
				}
				else {
					Container parent = (Container)c;
					pieces = new JLabel( new ImageIcon("BlackQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
					parent.add(pieces);
				}


			}
			else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);			
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);	            	
				}
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);									
			}
		}
		if(validMove){
			makeAIMove();
		}
    }


    public void mouseClicked(MouseEvent e) {
	
    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){
	
    }
    public void mouseExited(MouseEvent e) {
	
    }

	public Stack getKnightMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Stack attacking = new Stack();
		Square s = new Square(x + 1, y + 2, piece);
		moves.push(s);
		Square s1 = new Square(x + 1, y - 2, piece);
		moves.push(s1);
		Square s2 = new Square(x - 1, y + 2, piece);
		moves.push(s2);
		Square s3 = new Square(x - 1, y - 2, piece);
		moves.push(s3);
		Square s4 = new Square(x + 2, y + 1, piece);
		moves.push(s4);
		Square s5 = new Square(x + 2, y - 1, piece);
		moves.push(s5);
		Square s6 = new Square(x - 2, y + 1, piece);
		moves.push(s6);
		Square s7 = new Square(x - 2, y - 1, piece);
		moves.push(s7);

		for (int i = 0; i < 8; i++) {
			Square tmp = (Square) moves.pop();
			Move tmpmove = new Move(startingSquare, tmp, getPieceScore(tmp.getYC(), tmp.getYC(), getPieceName(tmp.getYC(), tmp.getYC())));
			if ((tmp.getXC() < 0) || (tmp.getXC() > 7) || (tmp.getYC() < 0) || (tmp.getYC() > 7)) {

			}
			else if (piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						attacking.push(tmpmove);
					}


			} else{
				attacking.push(tmpmove);
			}

		}

		return attacking;
	}

	public Stack getWhitePawnSquares(int x, int y, String piece) {
		Stack moves = new Stack();
		Square startingSquare = new Square(x, y, piece);
		Move validM;
		int tmpx1 = x + 1;
		int tmpx2 = x - 1;
		int tmpy1 = y + 1;
		int tmpy2 = y + 2;
		if (y == 1) {
			if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20)) {
				Square tmp = new Square(x, tmpy1, getPieceName(x, tmpy1));
				validM = new Move(startingSquare, tmp, getPieceScore(x, tmpy1, getPieceName(x,tmpy1)));
				moves.push(validM);
			}
			if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20) && !piecePresent((x * 75) + 20, (tmpy2 * 75) + 20)) {
				Square tmp = new Square(x, tmpy2, getPieceName(x, tmpy2));
				validM = new Move(startingSquare, tmp, getPieceScore(x, tmpy2, getPieceName(x,tmpy2)));
				moves.push(validM);
			}
			if (piecePresent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20) && tmpx1>=0 && tmpx1<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkWhiteOponent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1));
					validM = new Move(startingSquare, tmp, getPieceScore(tmpx1, tmpy1, getPieceName(tmpx1,tmpy1)));
					moves.push(validM);
				}
			}
			if (piecePresent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20) && tmpx2>=0 && tmpx2<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkWhiteOponent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx2, tmpy1, getPieceName(tmpx2, tmpy1));
					validM = new Move(startingSquare, tmp, getPieceScore(tmpx2, tmpy1, getPieceName(tmpx2,tmpy1)));
					moves.push(validM);
				}
			}
		} else {
			if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20) && tmpy1>=0 && tmpy1<=7) {
				Square tmp = new Square(x, tmpy1, getPieceName(x, tmpy1));
				validM = new Move(startingSquare, tmp, getPieceScore(x, tmpy1, getPieceName(x,tmpy1)));
				moves.push(validM);
			}
			if (piecePresent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20) && tmpx1>=0 && tmpx1<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkWhiteOponent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1));
					validM = new Move(startingSquare, tmp, getPieceScore(tmpx1, tmpy1, getPieceName(tmpx1,tmpy1)));
					moves.push(validM);
				}
			}
			if (piecePresent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20) && tmpx2>=0 && tmpx2<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkWhiteOponent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx2, tmpy1, getPieceName(tmpx2, tmpy1));
					validM = new Move(startingSquare, tmp, getPieceScore(tmpx2, tmpy1, getPieceName(tmpx2,tmpy1)));
					moves.push(validM);
				}
			}
		}

		return moves;
	}
	public Stack getBlackKnightMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Stack attacking = new Stack();
		Square s = new Square(x + 1, y + 2, piece);
		moves.push(s);
		Square s1 = new Square(x + 1, y - 2, piece);
		moves.push(s1);
		Square s2 = new Square(x - 1, y + 2, piece);
		moves.push(s2);
		Square s3 = new Square(x - 1, y - 2, piece);
		moves.push(s3);
		Square s4 = new Square(x + 2, y + 1, piece);
		moves.push(s4);
		Square s5 = new Square(x + 2, y - 1, piece);
		moves.push(s5);
		Square s6 = new Square(x - 2, y + 1, piece);
		moves.push(s6);
		Square s7 = new Square(x - 2, y - 1, piece);
		moves.push(s7);

		for (int i = 0; i < 8; i++) {
			Square tmp = (Square) moves.pop();
			Move tmpmove = new Move(startingSquare, tmp, getBlackPieceScore(tmp.getYC(), tmp.getYC(), getPieceName(tmp.getYC(), tmp.getYC())));
			if ((tmp.getXC() < 0) || (tmp.getXC() > 7) || (tmp.getYC() < 0) || (tmp.getYC() > 7)) {

			}
			else if (piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					if (checkBlackOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						attacking.push(tmpmove);
					}

			} else{
				attacking.push(tmpmove);
			}

		}

		return attacking;
	}

	public Stack getBlackPawnSquares(int x, int y, String piece) {
		Stack moves = new Stack();
		Square startingSquare = new Square(x, y, piece);
		Move validM;
		int tmpx1 = x + 1;
		int tmpx2 = x - 1;
		int tmpy1 = y + 1;
		int tmpy2 = y + 2;
		if (y == 6) {
			if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20)) {
				Square tmp = new Square(x, tmpy1, getPieceName(x, tmpy1));
				validM = new Move(startingSquare, tmp, getBlackPieceScore(x, tmpy1, getPieceName(x,tmpy1)));
				moves.push(validM);
			}
			if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20) && !piecePresent((x * 75) + 20, (tmpy2 * 75) + 20)) {
				Square tmp = new Square(x, tmpy2, getPieceName(x, tmpy2));
				validM = new Move(startingSquare, tmp, getBlackPieceScore(x, tmpy2, getPieceName(x,tmpy2)));
				moves.push(validM);
			}
			if (piecePresent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20) && tmpx1>=0 && tmpx1<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkBlackOponent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1));
					validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx1, tmpy1, getPieceName(tmpx1,tmpy1)));
					moves.push(validM);
				}
			}
			if (piecePresent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20) && tmpx2>=0 && tmpx2<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkBlackOponent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx2, tmpy1, getPieceName(tmpx2, tmpy1));
					validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx2, tmpy1, getPieceName(tmpx2,tmpy1)));
					moves.push(validM);
				}
			}
		} else {
			if (!piecePresent((x * 75) + 20, (tmpy1 * 75) + 20) && tmpy1>=0 && tmpy1<=7) {
				Square tmp = new Square(x, tmpy1, getPieceName(x, tmpy1));
				validM = new Move(startingSquare, tmp, getBlackPieceScore(x, tmpy1, getPieceName(x,tmpy1)));
				moves.push(validM);
			}
			if (piecePresent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20) && tmpx1>=0 && tmpx1<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkBlackOponent((tmpx1 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1));
					validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx1, tmpy1, getPieceName(tmpx1,tmpy1)));
					moves.push(validM);
				}
			}
			if (piecePresent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20) && tmpx2>=0 && tmpx2<=7 && tmpy1>=0 && tmpy1<=7) {
				if (checkBlackOponent((tmpx2 * 75) + 20, (tmpy1 * 75) + 20)) {
					Square tmp = new Square(tmpx2, tmpy1, getPieceName(tmpx2, tmpy1));
					validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx2, tmpy1, getPieceName(tmpx2,tmpy1)));
					moves.push(validM);
				}
			}
		}

		return moves;
	}
	public Stack getBlackKingSquares(int x, int y, String piece) {
		Stack moves = new Stack();
		Square startingSquare = new Square(x, y, piece);
		Move validM, validM2, validM3, validM4;
		int tmpx1 = x + 1;
		int tmpx2 = x - 1;
		int tmpy1 = y + 1;
		int tmpy2 = y - 1;

		if (!((tmpx1 > 7))) {
			Square tmp = new Square(tmpx1, y, piece);
			Square tmp1 = new Square(tmpx1, tmpy1, piece);
			Square tmp2 = new Square(tmpx1, tmpy2, piece);
			if (checkSurroundingSquares(tmp)) {
				validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx1, y, getPieceName(tmpx1, y)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkBlackOponent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
						moves.push(validM);
					}
				}
			}
			if (!(tmpy1 > 7)) {
				if (checkSurroundingSquares(tmp1)) {
					validM2 = new Move(startingSquare, tmp1, getBlackPieceScore(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp1.getXC() * 75) + 20), (((tmp1.getYC() * 75) + 20)))) {
						moves.push(validM2);
					} else {
						if (checkBlackOponent(((tmp1.getXC() * 75) + 20), (((tmp1.getYC() * 75) + 20)))) {
							moves.push(validM2);
						}
					}
				}
			}
			if (!(tmpy2 < 0)) {
				if (checkSurroundingSquares(tmp2)) {
					validM3 = new Move(startingSquare, tmp2, getBlackPieceScore(tmpx1, tmpy2, getPieceName(tmpx1, tmpy2)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
						moves.push(validM3);
					} else {
						System.out.println("The values that we are going to be looking at are : " + ((tmp2.getXC() * 75) + 20) + " and the y value is : " + ((tmp2.getYC() * 75) + 20));
						if (checkBlackOponent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
							moves.push(validM3);
						}
					}
				}
			}
		}
		if (!((tmpx2 < 0))) {
			Square tmp3 = new Square(tmpx2, y, piece);
			Square tmp4 = new Square(tmpx2, tmpy1, piece);
			Square tmp5 = new Square(tmpx2, tmpy2, piece);
			if (checkSurroundingSquares(tmp3)) {
				validM = new Move(startingSquare, tmp3, getBlackPieceScore(tmpx2, y, getPieceName(tmpx2, y)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkBlackOponent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
						moves.push(validM);
					}
				}
			}
			if (!(tmpy1 > 7)) {
				if (checkSurroundingSquares(tmp4)) {
					validM2 = new Move(startingSquare, tmp4, getBlackPieceScore(tmpx2, tmpy1, getPieceName(tmpx2, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
						moves.push(validM2);
					} else {
						if (checkBlackOponent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
							moves.push(validM2);
						}
					}
				}
			}
			if (!(tmpy2 < 0)) {
				if (checkSurroundingSquares(tmp5)) {
					validM3 = new Move(startingSquare, tmp5, getBlackPieceScore(tmpx2, tmpy2, getPieceName(tmpx2, tmpy2)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp5.getXC() * 75) + 20), (((tmp5.getYC() * 75) + 20)))) {
						moves.push(validM3);
					} else {
						if (checkBlackOponent(((tmp5.getXC() * 75) + 20), (((tmp5.getYC() * 75) + 20)))) {
							moves.push(validM3);
						}
					}
				}
			}
		}
		Square tmp7 = new Square(x, tmpy1, piece);
		Square tmp8 = new Square(x, tmpy2, piece);
		if (!(tmpy1 > 7)) {
			if (checkSurroundingSquares(tmp7)) {
				validM2 = new Move(startingSquare, tmp7, getBlackPieceScore(x, tmpy1, getPieceName(x, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp7.getXC() * 75) + 20), (((tmp7.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkBlackOponent(((tmp7.getXC() * 75) + 20), (((tmp7.getYC() * 75) + 20)))) {
						moves.push(validM2);
					}
				}
			}
		}
		if (!(tmpy2 < 0)) {
			if (checkSurroundingSquares(tmp8)) {
				validM3 = new Move(startingSquare, tmp8, getBlackPieceScore(x, tmpy2, getPieceName(x, tmpy2)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp8.getXC() * 75) + 20), (((tmp8.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkBlackOponent(((tmp8.getXC() * 75) + 20), (((tmp8.getYC() * 75) + 20)))) {
						moves.push(validM3);
					}
				}
			}
		}
		return moves;
	} // end of the method getKingSquares()

	/*
	 * Method to return all the possible moves that a Queen can make
	 */
	public Stack getBlackQueenMoves(int x, int y, String piece) {
		Stack completeMoves = new Stack();
		Stack tmpMoves = new Stack();
		Move tmp;
		/*
		 * The Queen is a pretty easy piece to figure out if you have completed the
		 * Bishop and the Rook movements. Either the Queen is going to move like a
		 * Bishop or its going to move like a Rook, so all we have to do is make a call
		 * to both of these methods.
		 */
		tmpMoves = getBlackRookMoves(x, y, piece);
		while (!tmpMoves.empty()) {
			tmp = (Move) tmpMoves.pop();
			completeMoves.push(tmp);
		}
		tmpMoves = getBlackBishopMoves(x, y, piece);
		while (!tmpMoves.empty()) {
			tmp = (Move) tmpMoves.pop();
			completeMoves.push(tmp);
		}
		return completeMoves;
	}

	public Stack getBlackRookMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Move validM, validM2, validM3, validM4;

		for (int i = 1; i < 8; i++) {
			int tmpx = x + i;
			int tmpy = y;
			if (!(tmpx > 7 || tmpx < 0)) {
				Square tmp = new Square(tmpx, tmpy, piece);
				validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx, tmpy, getPieceName(tmpx, tmpy)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkBlackOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						moves.push(validM);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int j = 1; j < 8; j++) {
			int tmpx1 = x - j;
			int tmpy1 = y;
			if (!(tmpx1 > 7 || tmpx1 < 0)) {
				Square tmp2 = new Square(tmpx1, tmpy1, piece);
				validM2 = new Move(startingSquare, tmp2, getBlackPieceScore(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkBlackOponent(((tmp2.getXC() * 75) + 20), ((tmp2.getYC() * 75) + 20))) {
						moves.push(validM2);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int k = 1; k < 8; k++) {
			int tmpx3 = x;
			int tmpy3 = y + k;
			if (!(tmpy3 > 7 || tmpy3 < 0)) {
				Square tmp3 = new Square(tmpx3, tmpy3, piece);
				validM3 = new Move(startingSquare, tmp3, getBlackPieceScore(tmpx3, tmpy3, getPieceName(tmpx3, tmpy3)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkBlackOponent(((tmp3.getXC() * 75) + 20), ((tmp3.getYC() * 75) + 20))) {
						moves.push(validM3);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int l = 1; l < 8; l++) {
			int tmpx4 = x;
			int tmpy4 = y - l;
			if (!(tmpy4 > 7 || tmpy4 < 0)) {
				Square tmp4 = new Square(tmpx4, tmpy4, piece);
				validM4 = new Move(startingSquare, tmp4, getBlackPieceScore(tmpx4, tmpy4, getPieceName(tmpx4, tmpy4)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
					moves.push(validM4);
				} else {
					if (checkBlackOponent(((tmp4.getXC() * 75) + 20), ((tmp4.getYC() * 75) + 20))) {
						moves.push(validM4);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		return moves;
	}// end of get Rook Moves.

	public Stack getBlackBishopMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Move validM, validM2, validM3, validM4;

		for (int i = 1; i < 8; i++) {
			int tmpx = x + i;
			int tmpy = y + i;
			if (!(tmpx > 7 || tmpx < 0 || tmpy > 7 || tmpy < 0)) {
				Square tmp = new Square(tmpx, tmpy, piece);
				validM = new Move(startingSquare, tmp, getBlackPieceScore(tmpx, tmpy, getPieceName(tmpx, tmpy)));
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkBlackOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						moves.push(validM);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the first for Loop
		for (int k = 1; k < 8; k++) {
			int tmpk = x + k;
			int tmpy2 = y - k;
			if (!(tmpk > 7 || tmpk < 0 || tmpy2 > 7 || tmpy2 < 0)) {
				Square tmpK1 = new Square(tmpk, tmpy2, piece);
				validM2 = new Move(startingSquare, tmpK1, getBlackPieceScore(tmpk, tmpy2, getPieceName(tmpk, tmpy2)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpK1.getXC() * 75) + 20), (((tmpK1.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkBlackOponent(((tmpK1.getXC() * 75) + 20), ((tmpK1.getYC() * 75) + 20))) {
						moves.push(validM2);
						break;
					} else {
						break;
					}
				}
			}
		} // end of second loop.
		for (int l = 1; l < 8; l++) {
			int tmpL2 = x - l;
			int tmpy3 = y + l;
			if (!(tmpL2 > 7 || tmpL2 < 0 || tmpy3 > 7 || tmpy3 < 0)) {
				Square tmpLMov2 = new Square(tmpL2, tmpy3, piece);
				validM3 = new Move(startingSquare, tmpLMov2, getBlackPieceScore(tmpL2, tmpy3, getPieceName(tmpL2, tmpy3)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpLMov2.getXC() * 75) + 20), (((tmpLMov2.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkBlackOponent(((tmpLMov2.getXC() * 75) + 20), ((tmpLMov2.getYC() * 75) + 20))) {
						moves.push(validM3);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the third loop
		for (int n = 1; n < 8; n++) {
			int tmpN2 = x - n;
			int tmpy4 = y - n;
			if (!(tmpN2 > 7 || tmpN2 < 0 || tmpy4 > 7 || tmpy4 < 0)) {
				Square tmpNmov2 = new Square(tmpN2, tmpy4, piece);
				validM4 = new Move(startingSquare, tmpNmov2, getBlackPieceScore(tmpN2, tmpy4, getPieceName(tmpN2, tmpy4)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpNmov2.getXC() * 75) + 20), (((tmpNmov2.getYC() * 75) + 20)))) {
					moves.push(validM4);
				} else {
					if (checkBlackOponent(((tmpNmov2.getXC() * 75) + 20), ((tmpNmov2.getYC() * 75) + 20))) {
						moves.push(validM4);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the last loop
		return moves;
	}

	public Stack getKingSquares(int x, int y, String piece) {
		Stack moves = new Stack();
		Square startingSquare = new Square(x, y, piece);
		Move validM, validM2, validM3, validM4;
		int tmpx1 = x + 1;
		int tmpx2 = x - 1;
		int tmpy1 = y + 1;
		int tmpy2 = y - 1;

		if (!((tmpx1 > 7))) {
			Square tmp = new Square(tmpx1, y, piece);
			Square tmp1 = new Square(tmpx1, tmpy1, piece);
			Square tmp2 = new Square(tmpx1, tmpy2, piece);
			if (checkSurroundingSquares(tmp)) {
				validM = new Move(startingSquare, tmp, getPieceScore(tmpx1, y, getPieceName(tmpx1, y)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
						moves.push(validM);
					}
				}
			}
			if (!(tmpy1 > 7)) {
				if (checkSurroundingSquares(tmp1)) {
					validM2 = new Move(startingSquare, tmp1, getPieceScore(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp1.getXC() * 75) + 20), (((tmp1.getYC() * 75) + 20)))) {
						moves.push(validM2);
					} else {
						if (checkWhiteOponent(((tmp1.getXC() * 75) + 20), (((tmp1.getYC() * 75) + 20)))) {
							moves.push(validM2);
						}
					}
				}
			}
			if (!(tmpy2 < 0)) {
				if (checkSurroundingSquares(tmp2)) {
					validM3 = new Move(startingSquare, tmp2, getPieceScore(tmpx1, tmpy2, getPieceName(tmpx1, tmpy2)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
						moves.push(validM3);
					} else {
						System.out.println("The values that we are going to be looking at are : " + ((tmp2.getXC() * 75) + 20) + " and the y value is : " + ((tmp2.getYC() * 75) + 20));
						if (checkWhiteOponent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
							moves.push(validM3);
						}
					}
				}
			}
		}
		if (!((tmpx2 < 0))) {
			Square tmp3 = new Square(tmpx2, y, piece);
			Square tmp4 = new Square(tmpx2, tmpy1, piece);
			Square tmp5 = new Square(tmpx2, tmpy2, piece);
			if (checkSurroundingSquares(tmp3)) {
				validM = new Move(startingSquare, tmp3, getPieceScore(tmpx2, y, getPieceName(tmpx2, y)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
						moves.push(validM);
					}
				}
			}
			if (!(tmpy1 > 7)) {
				if (checkSurroundingSquares(tmp4)) {
					validM2 = new Move(startingSquare, tmp4, getPieceScore(tmpx2, tmpy1, getPieceName(tmpx2, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
						moves.push(validM2);
					} else {
						if (checkWhiteOponent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
							moves.push(validM2);
						}
					}
				}
			}
			if (!(tmpy2 < 0)) {
				if (checkSurroundingSquares(tmp5)) {
					validM3 = new Move(startingSquare, tmp5, getPieceScore(tmpx2, tmpy2, getPieceName(tmpx2, tmpy2)));		//Added getPieceScore Here | Used to send weighting of piece.
					if (!piecePresent(((tmp5.getXC() * 75) + 20), (((tmp5.getYC() * 75) + 20)))) {
						moves.push(validM3);
					} else {
						if (checkWhiteOponent(((tmp5.getXC() * 75) + 20), (((tmp5.getYC() * 75) + 20)))) {
							moves.push(validM3);
						}
					}
				}
			}
		}
		Square tmp7 = new Square(x, tmpy1, piece);
		Square tmp8 = new Square(x, tmpy2, piece);
		if (!(tmpy1 > 7)) {
			if (checkSurroundingSquares(tmp7)) {
				validM2 = new Move(startingSquare, tmp7, getPieceScore(x, tmpy1, getPieceName(x, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp7.getXC() * 75) + 20), (((tmp7.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkWhiteOponent(((tmp7.getXC() * 75) + 20), (((tmp7.getYC() * 75) + 20)))) {
						moves.push(validM2);
					}
				}
			}
		}
		if (!(tmpy2 < 0)) {
			if (checkSurroundingSquares(tmp8)) {
				validM3 = new Move(startingSquare, tmp8, getPieceScore(x, tmpy2, getPieceName(x, tmpy2)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp8.getXC() * 75) + 20), (((tmp8.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkWhiteOponent(((tmp8.getXC() * 75) + 20), (((tmp8.getYC() * 75) + 20)))) {
						moves.push(validM3);
					}
				}
			}
		}
		return moves;
	} // end of the method getKingSquares()

	/*
	 * Method to return all the possible moves that a Queen can make
	 */
	public Stack getQueenMoves(int x, int y, String piece) {
		Stack completeMoves = new Stack();
		Stack tmpMoves = new Stack();
		Move tmp;
		/*
		 * The Queen is a pretty easy piece to figure out if you have completed the
		 * Bishop and the Rook movements. Either the Queen is going to move like a
		 * Bishop or its going to move like a Rook, so all we have to do is make a call
		 * to both of these methods.
		 */
		tmpMoves = getRookMoves(x, y, piece);
		while (!tmpMoves.empty()) {
			tmp = (Move) tmpMoves.pop();
			completeMoves.push(tmp);
		}
		tmpMoves = getBishopMoves(x, y, piece);
		while (!tmpMoves.empty()) {
			tmp = (Move) tmpMoves.pop();
			completeMoves.push(tmp);
		}
		return completeMoves;
	}

	public Stack getRookMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Move validM, validM2, validM3, validM4;

		for (int i = 1; i < 8; i++) {
			int tmpx = x + i;
			int tmpy = y;
			if (!(tmpx > 7 || tmpx < 0)) {
				Square tmp = new Square(tmpx, tmpy, piece);
				validM = new Move(startingSquare, tmp, getPieceScore(tmpx, tmpy, getPieceName(tmpx, tmpy)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						moves.push(validM);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int j = 1; j < 8; j++) {
			int tmpx1 = x - j;
			int tmpy1 = y;
			if (!(tmpx1 > 7 || tmpx1 < 0)) {
				Square tmp2 = new Square(tmpx1, tmpy1, piece);
				validM2 = new Move(startingSquare, tmp2, getPieceScore(tmpx1, tmpy1, getPieceName(tmpx1, tmpy1)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp2.getXC() * 75) + 20), (((tmp2.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkWhiteOponent(((tmp2.getXC() * 75) + 20), ((tmp2.getYC() * 75) + 20))) {
						moves.push(validM2);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int k = 1; k < 8; k++) {
			int tmpx3 = x;
			int tmpy3 = y + k;
			if (!(tmpy3 > 7 || tmpy3 < 0)) {
				Square tmp3 = new Square(tmpx3, tmpy3, piece);
				validM3 = new Move(startingSquare, tmp3, getPieceScore(tmpx3, tmpy3, getPieceName(tmpx3, tmpy3)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp3.getXC() * 75) + 20), (((tmp3.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkWhiteOponent(((tmp3.getXC() * 75) + 20), ((tmp3.getYC() * 75) + 20))) {
						moves.push(validM3);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		for (int l = 1; l < 8; l++) {
			int tmpx4 = x;
			int tmpy4 = y - l;
			if (!(tmpy4 > 7 || tmpy4 < 0)) {
				Square tmp4 = new Square(tmpx4, tmpy4, piece);
				validM4 = new Move(startingSquare, tmp4, getPieceScore(tmpx4, tmpy4, getPieceName(tmpx4, tmpy4)));		//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp4.getXC() * 75) + 20), (((tmp4.getYC() * 75) + 20)))) {
					moves.push(validM4);
				} else {
					if (checkWhiteOponent(((tmp4.getXC() * 75) + 20), ((tmp4.getYC() * 75) + 20))) {
						moves.push(validM4);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the loop with x increasing and Y doing nothing...
		return moves;
	}// end of get Rook Moves.

	public Stack getBishopMoves(int x, int y, String piece) {
		Square startingSquare = new Square(x, y, piece);
		Stack moves = new Stack();
		Move validM, validM2, validM3, validM4;

		for (int i = 1; i < 8; i++) {
			int tmpx = x + i;
			int tmpy = y + i;
			if (!(tmpx > 7 || tmpx < 0 || tmpy > 7 || tmpy < 0)) {
				Square tmp = new Square(tmpx, tmpy, piece);
				validM = new Move(startingSquare, tmp, getPieceScore(tmpx, tmpy, getPieceName(tmpx, tmpy)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
					moves.push(validM);
				} else {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						moves.push(validM);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the first for Loop
		for (int k = 1; k < 8; k++) {
			int tmpk = x + k;
			int tmpy2 = y - k;
			if (!(tmpk > 7 || tmpk < 0 || tmpy2 > 7 || tmpy2 < 0)) {
				Square tmpK1 = new Square(tmpk, tmpy2, piece);
				validM2 = new Move(startingSquare, tmpK1, getPieceScore(tmpk, tmpy2, getPieceName(tmpk, tmpy2)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpK1.getXC() * 75) + 20), (((tmpK1.getYC() * 75) + 20)))) {
					moves.push(validM2);
				} else {
					if (checkWhiteOponent(((tmpK1.getXC() * 75) + 20), ((tmpK1.getYC() * 75) + 20))) {
						moves.push(validM2);
						break;
					} else {
						break;
					}
				}
			}
		} // end of second loop.
		for (int l = 1; l < 8; l++) {
			int tmpL2 = x - l;
			int tmpy3 = y + l;
			if (!(tmpL2 > 7 || tmpL2 < 0 || tmpy3 > 7 || tmpy3 < 0)) {
				Square tmpLMov2 = new Square(tmpL2, tmpy3, piece);
				validM3 = new Move(startingSquare, tmpLMov2, getPieceScore(tmpL2, tmpy3, getPieceName(tmpL2, tmpy3)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpLMov2.getXC() * 75) + 20), (((tmpLMov2.getYC() * 75) + 20)))) {
					moves.push(validM3);
				} else {
					if (checkWhiteOponent(((tmpLMov2.getXC() * 75) + 20), ((tmpLMov2.getYC() * 75) + 20))) {
						moves.push(validM3);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the third loop
		for (int n = 1; n < 8; n++) {
			int tmpN2 = x - n;
			int tmpy4 = y - n;
			if (!(tmpN2 > 7 || tmpN2 < 0 || tmpy4 > 7 || tmpy4 < 0)) {
				Square tmpNmov2 = new Square(tmpN2, tmpy4, piece);
				validM4 = new Move(startingSquare, tmpNmov2, getPieceScore(tmpN2, tmpy4, getPieceName(tmpN2, tmpy4)));	//Added getPieceScore Here | Used to send weighting of piece.
				if (!piecePresent(((tmpNmov2.getXC() * 75) + 20), (((tmpNmov2.getYC() * 75) + 20)))) {
					moves.push(validM4);
				} else {
					if (checkWhiteOponent(((tmpNmov2.getXC() * 75) + 20), ((tmpNmov2.getYC() * 75) + 20))) {
						moves.push(validM4);
						break;
					} else {
						break;
					}
				}
			}
		} // end of the last loop
		return moves;
	}
//	private Stack getWhiteAttackingSquares(Stack Pieces){
//
//	}

	public Move twoLevelsDeep(Stack possibilities){
		Random rand;
		rand = new Random();
		Stack temporary = (Stack)possibilities.clone();
		Square s=new Square(75,75,"BlackPawn");
		Square s1=new Square(75,75,"BlackPawn");
		Move selectedMove = new Move(s1,s,-1);
		int moveID = rand.nextInt(possibilities.size());
		Move tmp;
		Move tmp1,tmp2 = new Move();
		tmp2.moveScore=-999;
		int counter= possibilities.size();
		for(int i=0;i< counter;i++){
			tmp=(Move)possibilities.pop();
			Stack black=blackAttacks();
			int counter1=black.size();
			for(int j=0;j<counter1;j++){
				tmp1=(Move)black.pop();
				if(tmp1.getScore()>tmp2.getScore()){
					tmp2=tmp1;
				}
			}

			for (int p=0;p<counter;p++){
				if(selectedMove.getScore()<(tmp.getScore()- tmp2.getScore())){
					selectedMove=tmp;
				}
			}
		}
		if(selectedMove.getScore()<=0){
			for(int i=1;i < (temporary.size()-(moveID));i++){
				temporary.pop();
			}
			selectedMove = (Move)temporary.pop();
		}
		System.out.println(selectedMove.getScore()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return selectedMove;
	}


	public void makeAIMove(){
  /*
    When the AI Agent decides on a move, a red border shows the square from where the move started and the
    landing square of the move.
  */
		resetBorders();
		layeredPane.validate();
		layeredPane.repaint();

		Stack white = findWhitePieces();
		Stack completeMoves = new Stack();
		Move tmp;
		Stack temporary = new Stack();
		while(!white.empty()){
			Square s = (Square)white.pop();
			String tmpString = s.getName();
			Stack tmpMoves = new Stack();

    /*
        We need to identify all the possible moves that can be made by the AI Opponent
    */
			if(tmpString.contains("Knight")){
				    tmpMoves = getKnightMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Bishop")){
				    tmpMoves = getBishopMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Pawn")){
					tmpMoves = getWhitePawnSquares(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Rook")){
				  tmpMoves = getRookMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("Queen")){
				  tmpMoves = getQueenMoves(s.getXC(), s.getYC(), s.getName());
			}
			else if(tmpString.contains("King")){
				tmpMoves = getKingSquares(s.getXC(), s.getYC(), s.getName());
			}

			while(!tmpMoves.empty()){
				tmp = (Move)tmpMoves.pop();
				completeMoves.push(tmp);
			}
		}
		temporary = (Stack)completeMoves.clone();
		getLandingSquares(temporary);
		printStack(temporary);
/*
So now we should have a copy of all the possible moves to make in our Stack called completeMoves
*/
		if(completeMoves.size() == 0){
/*
    In Chess if you cannot make a valid move but you are not in Check this state is referred to
    as a Stale Mate
*/
			JOptionPane.showMessageDialog(null, "Cogratulations, you have placed the AI component in a Stale Mate Position");
			System.exit(0);

		}
		else{
  /*
    Okay, so we can make a move now. We have a stack of all possible moves and need to call the correct agent to select
    one of these moves. Lets print out the possible moves to the standard output to view what the options are for
    White. Later when you are finished the continuous assessment you don't need to have such information being printed
    out to the standard output.
  */
			System.out.println("=============================================================");
			Stack testing = new Stack();
			while(!completeMoves.empty()){
				Move tmpMove = (Move)completeMoves.pop();
				Square s1 = (Square)tmpMove.getStart();
				Square s2 = (Square)tmpMove.getLanding();
				System.out.println("The "+s1.getName()+" can move from ("+s1.getXC()+", "+s1.getYC()+") to the following square: ("+s2.getXC()+", "+s2.getYC()+")");
				testing.push(tmpMove);
			}
			System.out.println("=============================================================");
			Border redBorder = BorderFactory.createLineBorder(Color.RED, 3);
			Move selectedMove = twoLevelsDeep(testing);
			Square startingPoint = (Square)selectedMove.getStart();
			Square landingPoint = (Square)selectedMove.getLanding();
			int startX1 = (startingPoint.getXC()*75)+20;
			int startY1 = (startingPoint.getYC()*75)+20;
			int landingX1 = (landingPoint.getXC()*75)+20;
			int landingY1 = (landingPoint.getYC()*75)+20;
			System.out.println("-------- Move "+startingPoint.getName()+" ("+startingPoint.getXC()+", "+startingPoint.getYC()+") to ("+landingPoint.getXC()+", "+landingPoint.getYC()+")");

			Component c  = (JLabel)chessBoard.findComponentAt(startX1, startY1);
			Container parent = c.getParent();
			parent.remove(c);
			int panelID = (startingPoint.getYC() * 8)+startingPoint.getXC();
			panels = (JPanel)chessBoard.getComponent(panelID);
			panels.setBorder(redBorder);
			parent.validate();

			Component l = chessBoard.findComponentAt(landingX1, landingY1);
			if(l instanceof JLabel){
				Container parentlanding = l.getParent();
				JLabel awaitingName = (JLabel)l;
				String agentCaptured = awaitingName.getIcon().toString();
				if(agentCaptured.contains("King")){
					agentwins = true;
				}
				parentlanding.remove(l);
				parentlanding.validate();
				pieces = new JLabel( new ImageIcon(startingPoint.getName()+".png") );
				int landingPanelID = (landingPoint.getYC()*8)+landingPoint.getXC();
				panels = (JPanel)chessBoard.getComponent(landingPanelID);
				panels.add(pieces);
				panels.setBorder(redBorder);
				layeredPane.validate();
				layeredPane.repaint();

				if(agentwins){
					JOptionPane.showMessageDialog(null, "The AI Agent has won!");
					System.exit(0);
				}
			}
			else{
				pieces = new JLabel( new ImageIcon(startingPoint.getName()+".png") );
				int landingPanelID = (landingPoint.getYC()*8)+landingPoint.getXC();
				panels = (JPanel)chessBoard.getComponent(landingPanelID);
				panels.add(pieces);
				panels.setBorder(redBorder);
				layeredPane.validate();
				layeredPane.repaint();
			}
			white2Move = false;
		}
	}

	/*
		Main method that gets the ball moving.
	*/
	public void checkMate(int landingX1,int landingY1){
		Component l = chessBoard.findComponentAt(landingX1, landingY1);
//		Component l1 = chessBoard.findComponentAt(landingX1 -75, landingY1-75);
//		Component l2 = chessBoard.findComponentAt(landingX1, landingY1-75);
//		Component l3 = chessBoard.findComponentAt(landingX1+75, landingY1-75);
//		Component l4 = chessBoard.findComponentAt(landingX1-75, landingY1);
//		Component l5 = chessBoard.findComponentAt(landingX1+75, landingY1);
//		Component l6 = chessBoard.findComponentAt(landingX1-75, landingY1+75);
//		Component l7 = chessBoard.findComponentAt(landingX1, landingY1+75);
//		Component l8 = chessBoard.findComponentAt(landingX1+75, landingY1+75);
//		ArrayList<Component> c=new ArrayList<>();
//		c.add(l1);
//		c.add(l2);
//		c.add(l3);
//		c.add(l4);
//		c.add(l5);
//		c.add(l6);
//		c.add(l7);
//		c.add(l8);
//		for(int i=0;i<c.size();i++){
//			if(c.get(i) instanceof JLabel){
//				Container parentlanding = c.get(i).getParent();
//				JLabel awaitingName = (JLabel)c.get(i);
//				String agentCaptured = awaitingName.getIcon().toString();
//				if(agentCaptured.contains("King")){
//					JOptionPane.showMessageDialog(null, "You have won!");
//					System.exit(0);
//				}
//			}
//		}
		if(l instanceof JLabel){
			Container parentlanding = l.getParent();
			JLabel awaitingName = (JLabel)l;
			String agentCaptured = awaitingName.getIcon().toString();
			if(agentCaptured.contains("King")){
				JOptionPane.showMessageDialog(null, "You have won!");
				System.exit(0);
			}



		}
	}

	public static void initalCreation() {
		ChessProject frame = new ChessProject();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
    public static void main(String[] args) {

		initalCreation();
     }
}


