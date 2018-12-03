==dev==

SWAnalysisProject (H2)

Develop a library application to support the librarian.
図書館職員を助ける図書館アプリケーションの開発

- 도서관 사서가 사용하는 도서관 어플리케이션
- 도서관은 이름이 있고, 엄청많은 책(title(1개),author(1이상), unique catalogue number(중복되지않는 분류번호))들을 수장하고 있다.
- 도서관에는 대출자(중복되지않는 이름(1개)(같은 이름 중복 불가))들이 등록되어있다.
    - 사전조건: 이용하기 위해서는 등록을 해야한다.
- 대출자는 '한'권의 책의 대출과 반납을 할수있다. - 한명의 이용자는 한권을 대출할 수 있다.
- 각각의 책의 대출과 반납은 사서가 반드시 기록해야한다.
- 그녀는 새로운 이용자를 등록(한번에 한명씩), 대출가능한 책을 오름차순 보여준다, 대출중인 책을 오름차순으로 보여준다.

The library has a name, holds a number of books each of which has a title, 
author and unique catalogue number. There are registered borroewers a book
 and return it . However, each book transaction must be recorded by a librarian.
She is also expected to register a new borrower, be able to display in increasing
catalogue number order those books available for loan and those already out on loan.

図書館には名前があり、それぞれにタイトル、著者（複数可）、一意のカタログ番号（重複していない）
を持つ多数の書籍が保管されています。本にはボロウワーが登録されています。
ただし、各書籍取引は図書館員が記録しなければなりません。
また、新しい借り手を登録し、貸出可能な書
籍とすでに貸出中の書籍を、カタログ番号順に表示できるようにする予定です。

（＊同じタイトル、著者でもカタログ番号は違う）
（＊同じ名前の人は登録できない）
（＊カタログは大きい順に整列をする）
（＊貸出中の本、貸出可能な本を表示することができる）

図書館職員　ディスプレイ２、人の登録、貸出、受取、本の登録

