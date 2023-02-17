# 技術課題(gaudiy)


### 要件

  - 独自コインの残高を管理するシステムになります。
  - 事前にユーザー情報を登録することによって、そのユーザーは独自コインを管理できるようになります。
      - 登録したときに、ユーザーを一意に表すユーザーIDが生成されます。
  - ユーザーIDを指定することによって、ユーザーの残高を確認することができます。
  - ユーザーIDと増加量を指定することによって、残高に独自コインを追加することができます。
  - ユーザーIDと消費量を指定することによって、残高から独自コインを指定した分、減らすことができます。
  - Aさん → Bさんへの独自コインの送金も行うことができます。
  - ユーザーIDを指定することによって、今までどんな増加・消費がされたか日時付きで取得することができます。

### テック

* [markdown-it](https://github.com/markdown-it/markdown-it)
* [java](https://www.java.com/)
* [maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [docker](https://www.docker.com/)
* [docker-compose](https://github.com/docker/compose)
* [MySQL](https://www.mysql.com/)
* [Postman](https://www.postman.com/)

### データベース

DOCKERを使わないと自分で新規作成してください

```sh
CREATE SCHEMA `gaudiy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
CREATE TABLE `gaudiy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `coin` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
CREATE TABLE `gaudiy`.`coin_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `coin` INT NOT NULL,
  `remark` VARCHAR(255) NOT NULL,
  `create_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`user_id` ASC, `create_time` ASC));


```

### インストール

docker(https://www.docker.com/) とdocker-compose(https://github.com/docker/compose) が必要です。
dockerをインストールして、以下のコードで動かして環境はセットアップされます。

```sh
$ cd gaudiy
$ docker-compose up -d --build
```

### コンフィグレーション

サーバーのコンフィグファイルは
```sh
server\src\main\resources\application.properties
```

データベースを設定できます。


### テスト
gaudiy.postman_collection.jsonというファイルをPostManにインポートしたら、
全部のREST APIもすぐアクセスできるようになりました。


### 筆記試験

  - 複数のBE はk8sで本番環境を構築するのがいいと思うます。
    k8sはcontainerを使って、dockerも一緒使われます。
    Dockerを使う時は信頼できるImageを使うべきです。
    信頼できないimageはbackdoorが入れられた可能性があります。
    k8sを使うとサーバーの性能をアップダウンは簡単に操作できます。
    備えのcontainerがあればサーバーダウンされる可能性も低くなります。
    もしマイクルサービスのBEもあればk8s以内でアクセスの制限もできます。

  - もし静態のファイルが保存される時が必要な場合は、
    今使えるcloud(OBS)が多く、ダウンロードリンクにtokenとダウンロードタイムも簡単に設定できます。
    そしtokenとダウンロードタイムが必要じゃない場合はキャッシュできるようなサービス(CDN)も推奨です。
    もしDomainはcloudflareを用いて、サーバーのIPをproxyにして隠されることもできます。

  - 複数のBEに複数はマイクルサービスと思われます。
    サーバーとサーバーのコミュニケーションはGRPC+Protobufで接続することも推奨です。
    サーバーとクライアントを接続する時はHTTPSで利用して、クライアント(アプリ)側にCERT PINを要求して、
    クライアントが編集されない場合はMITMできないので安全です。
    サイトはCERT PINできないと思うので、HTTPSも不安全(MITM)とブラウザーもネットのREQとRESを簡単に見えるので、
    暗号化されることは必要ですが、少なくともJWTを利用して、KEYはサーバー側で保存されます。
    RSAされたAESキーを返却して復号化するとレスポンスヘダーにRSA SIGN VERIFYという改竄チェックは保護できると思いますが、
    必ず守れることはないですが、技術も持っていない人に十分です。

  - デプロイについて、
    GITのbranchを利用して、タスクのcommitをmergeして、テストして、GitHubのCI/CDと連携したら、mergeされたcommitをTagをつけて、そういう方法でデプロイするのはおすすめです。
    デプロイを始めたら、
    自動的にDOCKERで新しいcontainerを作って、そのcontinaterはBEの環境をインストールされます。
    それが終わったら、BEのコードがデプロイされてから、起動されます。
    最後にk8s containerの変換を設定して、そういう形でダウンタイムも少なくなると思うます。
    もちろんk8sに構築するのも必要です
    最後にGitHubのCI/CDのページで成功か失敗かチェックします
    最初の構築するのが時間かかりますが、それができれば便利になります。



ライセンス
----

MIT
