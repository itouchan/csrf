# csrf

## クロスサイトリクエストフォージェリとは

掲示板などの別のサイトに用意したリンクを踏ませること等をきっかけとして
インターネットショッピングの最終決済やパスワードの変更等意図しない処理を実行される。

## クロスサイトスクリプトの違いは
どちらも「クロスサイト」という言葉が先頭につく。
どちらも別のサイトを経由する所は、同じ。
XSS（クロスサイトスクリプト）は攻撃スクリプトがブラウザ上で動き、セッション情報等のを盗まれるの対して、
CSRF（クロスサイトリクエストフォージェリ）はリクエストを送付して、不正な処理をサーバーサイドで実行する。

## クロスサイトリクエストフォージェリによる影響
（１）なりすましによる不正処理
　*・インターネットショッピングの最終決済
　*・パスワード、メールアドレスの変更

## クロスサイトリクエストフォージェリの攻撃方法
図）
https://www.ipa.go.jp/security/awareness/vendor/programmingv2/contents/img/w301.png

（１）とあるサイトにてログインをします。
（２）（１）の状態で、別サイトの掲示板にて罠のリンクを押下する。
（３）（１）のサイトにリクエストを送付され、不正な処理を実行する。

## 実際にやってみよう(対応前)
（１）掲示板に罠を仕掛ける
http://www31303ue.sakura.ne.jp:8080/javasec/csrfbefore/Keijiban
（２）とあるサイトにログイン（パスワードを確認）
http://www31303ue.sakura.ne.jp:8080/javasec/csrfbefore/Login
before/before
（３）掲示板の罠（リンク）をふむ
http://www31303ue.sakura.ne.jp:8080/javasec/csrfbefore/Keijiban
（４）ログアウト
（５）再度ログインする※パスワードが変更されている為ログインできない

## クロスサイトリクエストフォージェリ対策方法
原因）
HTTPリクエストに伴って送られてきたCookieに正規のセッションIDが入っていさえすれば、
そのリクエストが受け入れて、ユーザ本人の意志に反した処理を実行している為。
対策）
・トークン（秘密情報）を利用して正しいリクエストかチェックする。
・Referer が正しいリンク元かチェックする。

（１）自前で対策
　*・トークン
　*・Referer

（２）フレームワークにてCSRF対策
（CakePHP,Ruby on Rails,SpringFramework（Spring Security ））
　*・CakePHP
https://book.cakephp.org/3.0/ja/controllers/components/csrf.html

## 動作環境
AP:Tomcat
Lang:Java
