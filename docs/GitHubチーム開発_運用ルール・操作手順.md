# GitHubチーム開発 運用ルール・操作手順

## 1. この実習におけるGitHubの使い方

開発メンバーは、同じリポジトリを共同で使用する。実装前にIssueを確認し、Issue専用のブランチで変更する。実装とテストが済んだらPull Request（PR）を作成し、レビューを受けてから`master`に反映する。

```text
【指導員】設計書・Issue・Projectを準備
     ↓
【担当者】Issueに着手（Project: Todo → In Progress）
     ↓
【担当者】masterを更新 → Issue専用ブランチ作成
     ↓
【担当者】Eclipseで実装 → テスト → Commit → Push
     ↓
【担当者】GitHubでPR作成（Project: Review）
     ↓
【指導員】レビュー ── 修正が必要 → 同じブランチで修正・再Push
     ↓ 問題なし
【指導員】PRをmasterにマージ
     ↓
【担当者・指導員】IssueのClosed／ProjectのDoneを確認
     ↓
【全員】最新のmasterを取り込み、次のIssueへ
```

### 用語

| 用語 | 意味 | 実習での用途 |
|---|---|---|
| Repository（リポジトリ） | ソースコードと履歴を管理する場所 | チーム全体で`iot-service`を共有 |
| Clone | GitHub上のリポジトリをPCに複製する | 開発開始時に各自1回実施 |
| Issue | 作業の指示・進捗・問題を記録する | 原則「1件の作業＝1件のIssue」 |
| Project | Issueを一覧・ボードで管理する | チームの作業状況を共有 |
| Branch（ブランチ） | 独立して変更するための作業系列 | 原則「1 Issue＝1作業ブランチ」 |
| Commit | 変更内容をローカルリポジトリに記録する | 作業の区切りごとに実施 |
| Push | ローカルのCommitをGitHubに送る | 作業の保存・共有 |
| Pull | GitHubの変更をローカルに取り込む | `master`を更新するときに実施 |
| Pull Request（PR） | 変更のレビューと取り込みを依頼する | `master`へマージする前に作成 |
| Merge（マージ） | 変更を別のブランチに統合する | 原則指導員がPRを`master`に取り込む |

## 2. 全メンバー共通のルール

1. **`master`を直接変更・Pushしない。** 実装は必ずIssue専用ブランチで行う。
2. **作業前にIssueを読む。** 目的、対象ファイル、完成条件、関連テスト仕様書を確認する。
3. **1 Issueにつき原則1ブランチ・1 PRとする。** 異なるIssueの作業を1つのPRに混ぜない。
4. **変更をCommitする前に内容を確認する。** 意図しないファイル、認証情報、個人設定、生成ファイルは含めない。
5. **実装後に必ずテストする。** OK／NG／未実施・保留を区別し、PRに結果を記録する。
6. **PRを作成しただけでは完了としない。** レビュー、必要な修正、マージまで行って完了とする。
7. **他人のブランチを勝手に変更しない。** 共同修正が必要な場合は担当者と指導員に相談する。
8. **困ったら早めにIssueまたは対面で相談する。** コンフリクトやGitの警告を理解せず操作しない。
9. **GitHub上にパスワードや秘密情報を登録しない。** DBパスワード、トークンなどは環境変数等で管理する。
10. **進捗をGitHubに残す。** 実習の終わりに担当Issueへ短い進捗報告を記入する。

### 担当者と指導員の役割

| 操作・判断 | 担当者（訓練生） | 指導員 |
|---|---|---|
| Issueの作成・割当・優先順位 | 提案・相談 | 決定・管理 |
| Issueの実装・テスト | 実施 | 支援 |
| ProjectのStatus更新 | 着手時・PR作成時に実施 | 全体の整合性を確認 |
| Commit・Push | 実施 | 必要に応じて支援 |
| PRの作成 | 実施 | 必要に応じて支援 |
| コードレビュー | 慣れたら相互レビューに参加 | 最終判断 |
| `master`へのマージ | 原則しない | 原則実施 |
| 結合試験・不具合対応 | 全員で実施 | 作業調整・完了判定 |

## 3. GitHub Projectの運用

Projectのボードは、Issueの**Status**で次の4列を使用する。

| Status | 意味 | 移動するタイミング |
|---|---|---|
| `Todo` | 未着手 | Issueを登録したとき |
| `In Progress` | 実装・テスト中 | 担当者が作業を開始したとき |
| `Review` | PR提出済み・レビュー待ち | 担当者がPRを作成したとき |
| `Done` | 完了 | PRのマージ・Issueのクローズと完成条件の確認後 |

- Projectに登録するのは**リポジトリのIssue**とする。同じ仕事について別のDraft itemを作らない。
- PRは原則Projectのカードとして追加しない。PRはIssueから参照する。
- Issueには担当者（Assignee）と適切なLabel（例：`task`、`area:service`）を設定する。
- 開発中のIssueを`Done`に移動してはいけない。**PRがマージされ、必要なテストが完了した状態**をDoneとする。
- IssueのClosedとProjectのDoneが一致しない場合は指導員へ連絡する。

## 4. 初回準備（各メンバー、1回のみ）

### 4.1 GitHubへの参加

1. 指導員から送られたリポジトリの招待を承認する。
2. GitHubにログインし、`iot-service`を閲覧できることを確認する。
3. リポジトリの`Issues`、`Pull requests`、指定されたProjectを開けることを確認する。

### 4.2 EclipseでCloneする

Eclipseには通常、Git操作用の**EGit**が組み込まれている。メニューが見つからない場合は指導員に確認する。

1. Eclipseで **File → Import...** を選択する。
2. **Git → Projects from Git** を選択して **Next**。
3. **Clone URI** を選択する。
4. GitHubのリポジトリページで **Code → HTTPS** からURLをコピーし、URIに貼り付ける。
5. GitHubアカウント認証情報（事前に作成した認証トークン）を入力する。**GitHubアカウントの通常のパスワードをGitのHTTPS認証に使用しない。**
6. ブランチ一覧では原則`master`を含めて進み、保存先ディレクトリを指定する。
7. プロジェクトの取り込み方法を選択する。Mavenプロジェクトとして正しく認識されない場合は、Clone後に **File → Import... → Maven → Existing Maven Projects** から`pom.xml`があるフォルダを選択する。
8. **Package Explorer**に`iot-service`が表示され、`pom.xml`と`src/`が確認できればよい。

**注意：** 既存のリポジトリをCloneして使用するため、`git init`やEclipseの **Team → Share Project** で新しいリポジトリを作る必要はない。プロジェクトにGitのブランチ名が表示されているか確認する。

## 5. 日常の作業手順（Issueごとに繰り返す）

以下では、Issue `#12`を例に説明する。**Issue番号は説明用の例**なので、実際に割り当てられた番号に置き換える。

### 5.1 Issueを確認して着手する【GitHub】

1. Repository → **Issues** → 担当Issueを開く。
2. 対象ファイル、実装内容、完成条件、テスト仕様書を読む。
3. Assigneeが自分になっているか確認する。
4. ProjectのStatusを`In Progress`に変更する。

### 5.2 `master`を最新化する【Eclipse】

1. **Package Explorer**でプロジェクトを右クリック → **Team → Switch To → master**（表示方法はEclipseの版による）。
2. `master`をチェックアウトしたことを確認する。
3. プロジェクトを右クリック → **Team → Pull** を実行する。
4. エラーがなく完了したことを確認する。

**注意：** 未Commitの変更がある状態でブランチを切り替えたりPullしたりしない。先に変更内容を確認し、必要に応じてCommitするか指導員に相談する。

### 5.3 Issue専用ブランチを作る【Eclipse】

1. `master`を最新化した状態でプロジェクトを右クリックする。
2. **Team → Switch To → New Branch...** を選択する（または **Git Repositories** ビューで **Branches → Local** から作成）。
3. ブランチ名を入力し、**Checkout new branch** を有効にして作成する。
4. Eclipse上で新しいブランチ名になっていることを確認する。

命名規則：`issue-<Issue番号>-<短い英数字の説明>`

例：

```text
issue-12-repository-validation
issue-13-service-register
issue-14-web-api
```

### 5.4 実装とテストを行う【Eclipse】

1. Issueに指定されたファイルを修正する。
2. JUnit等のテストを実行する。テスト仕様書の期待結果と一致するか確認する。
3. 失敗したテストは原因を調べて修正し、再テストする。
4. 動作確認できた範囲をCommitする。作業の区切りごとに何度Commitしてもよい。

例：JUnitテストは対象テストクラスを右クリック → **Run As → JUnit Test**。Maven全体テストは **Run As → Maven test**、またはターミナルから`mvn test`を実行する。

**注意：** まだ実装していない部分があるため、全体テストの成否だけでなく、自分のIssueに対応したテストの合否を必ず確認する。

### 5.5 Commitする【Eclipse】

1. プロジェクトを右クリック → **Team → Commit...** を選択し、**Git Staging** ビューを表示する。
2. `Unstaged Changes`のファイルと差分を確認する。
3. Commitに含めるファイルを`Staged Changes`へ移す（Stageする）。
4. Commitメッセージに**何を変更したか**を書く。
5. **Commit** を実行する。

Commitメッセージの例：

```text
センサーデータ保存前のデータ検証処理を追加
JDBCによるデータ登録処理を追加
REST APIの入力エラーハンドリングを追加
```

### 5.6 Pushする【Eclipse】

1. プロジェクトを右クリック → **Team → Push Branch...**（環境によっては **Push to Upstream** や **Remote → Push...**）を選択する。
2. Push先が指導員指定の`iot-service`リポジトリ（通常`origin`）であることを確認する。
3. Pushするブランチが**自分のIssue用ブランチ**であることを確認する。
4. 実行後、GitHubの **Branches** などでブランチが作成されたことを確認する。

**PushはPRの作成ではない。** Push後にGitHubでPRを作る。

### 5.7 Pull Requestを作る【GitHub】

1. Repository → **Pull requests** → **New pull request** を選択する（Push後に表示される **Compare & pull request** でもよい）。
2. **base: `master`**、**compare: 自分のIssue用ブランチ** を指定する。
3. タイトル、実装内容、テスト結果、注意事項を入力する。
4. PR本文に、対象Issueを関連付けるための`Closes #12`などを記載する。
5. **Create pull request** をクリックする。
6. Projectで対象Issueを`Review`に変更し、指導員へ確認を依頼する。

PR本文例：

```markdown
## 対応Issue
Closes #12

## 実装内容
- SensorDataの必須項目を検証する処理を追加
- findAllの件数範囲チェックを追加

## テスト結果
- Test01: OK 9件、NG 0件、保留 0件
- 実行環境: Java 21 / Eclipse

## 補足
- 特になし
```

`Closes #12`は、PRがリポジトリの**デフォルトブランチ**にマージされた場合にIssueを自動クローズするための記述。

### 5.8 レビュー結果に対応する【GitHub＋Eclipse】

- 指導員はPRの**Files changed**で変更を確認し、必要な指摘をコメントする。
- 修正が必要な場合、担当者は**同じ作業ブランチ**で修正・テスト・Commit・Pushを行う。
- 既存のPRは自動的に更新されるため、**新しいPRを作り直さない**。
- コメントで指摘に回答し、再レビューを依頼する。
- テストがNG・保留のままの場合は、原則マージしない。例外は指導員が判断する。

### 5.9 マージ後の作業【GitHub＋Eclipse】

1. 指導員がレビュー・テスト結果を確認し、PRを`master`にマージする（基本は **Squash and merge**）。
2. 担当者はIssueが**Closed**、Projectが**Done**になっていることを確認する。
3. Eclipseで **Team → Switch To → master** を実行する。
4. **Team → Pull** で最新の`master`を取得する。
5. 不要になった作業ブランチは、マージと変更の取り込みを確認してから削除する。
6. 次のIssueを始める場合は、**更新した`master`から新しいブランチ**を作成する。

## 6. テスト仕様書とテスト結果

- テスト仕様書（テストケース・期待結果）は、`docs/tests/`に作成済みのものを使用し、変更が必要な場合には指導員に相談の上で行う。変更したものはコミットに含め、**チーム全員で共有**する。
- JUnitなどのテストコードは`src/test/`に置き、ソースコードと同じように管理する。
- テストの**実施記録**は、IssueまたはPRのコメントに記載する。
- 仕様書の期待結果が不適切と考えた場合は、自己判断で変更せず指導員に確認する。

### テスト結果の書き方

```markdown
# テスト結果（2026-11-18 / 担当B）
- 対象：Test01 Repository共通入力検証
- 結果：OK 8件、NG 1件、保留 0件

## NGの内容
- テストID：T01-03
- 期待結果：空白のsensorIdでIllegalArgumentException
- 実際の結果：例外が発生しない
- 対応：入力チェックを修正する

## 再テスト
- T01-03：OK
- 関連テスト：OK
- 最終結果：全9件OK
```

### NG発生時のルール

| 発見した時点 | 対応 |
|---|---|
| 実装中・PRマージ前 | **元のIssue・PRで対応**。修正→再テスト→同じPRでレビュー |
| PRマージ後の結合試験 | **新しいBug Issueを作成**。再現手順・期待結果・実際の結果・関連Issueを記録 |
| 環境問題で実行できない | `保留`とし、理由と必要な対応を記録して指導員に相談 |

Bug Issueも通常と同じく、`Todo → In Progress → Review → Done`で管理する。

## 7. 実習終了時の進捗報告

実習の終わりに、担当Issueへ次の形式で短くコメントする。

```markdown
### 進捗報告
- 今回完了：〇〇の実装、正常系テスト
- 困っていること：△△の原因を調査中
- 次回行うこと：異常系テスト、PR作成
```

- 作業途中でも、必要な変更はCommit・Pushする（Pushする前に秘密情報・不要ファイルを確認する）。
- 次に取り組む作業を明確にする。
- Statusが実際の作業状況と一致しているか確認する。

## 8. よくあるトラブルと対応

| 状況 | まず確認すること | 対応 |
|---|---|---|
| `master`で編集してしまった | CommitやPushをしたか | **そのままPushしない。** 指導員に相談して変更を安全に移す |
| Pullが失敗した | 未Commitの変更がないか | 変更を消さずにエラーメッセージを指導員に見せる |
| Pushが拒否された | ブランチ名・Push先・認証・リモートとの差 | エラーを確認し、安易にForce Pushしない |
| Merge conflictが発生した | 同じ箇所を複数人が編集したか | 変更を削除せず、担当者・指導員と一緒に解決する |
| PRで他Issueの変更が見える | base/compareとブランチ作成元 | PRをマージせず指導員に相談する |
| PRを作成したがIssueがClosedにならない | PRがマージ済みか、`Closes #番号`が正しいか、baseがデフォルトブランチか | 指導員に確認し、必要に応じて手動で整理する |
| EclipseでGitメニューが見つからない | プロジェクトがCloneしたリポジトリと関連付いているか | **Window → Show View → Other... → Git → Git Repositories**を確認する |
| DB・MQTTに接続できない | 環境変数、接続先、ポート、認証 | 設計書・READMEを確認し、設定値に秘密情報を含めず相談する |

**禁止事項：** 意味を理解せず`Force Push`、`Reset --hard`、履歴の書き換えを行わない。誤操作時も慌ててファイルを削除せず指導員に相談する。

## 9. 結合試験・プロジェクト完了

1. 全員が担当Issueを完了させ、必須のPRをマージする。
2. システム全体（MQTT受信→Service→CSV/Oracle保存→REST API→Web画面）の結合試験を実施する。
3. NGがあればBug Issueを作成し、修正・再テスト・PR・マージを行う。
4. 必須テストがすべてOKであることと、重大な未解決不具合がないことを指導員が確認する。
5. 指導員が`master`の最終状態にTag／Release（例：`v1.0.0`）を作成し、完成版を確定する。
6. 各自、担当Issue・工夫点・不具合への対応・チームでの連携について成果発表する。

## 10. Eclipse操作とコマンドの対照表（参考）

コマンド操作が必要な場合の参考。**実習ではEclipseの操作を基本**とする。

| やりたいこと | Eclipse / EGitの操作例 | Gitコマンド例 |
|---|---|---|
| masterに切り替える | Team → Switch To → master | `git switch master` |
| masterを最新化する | Team → Pull | `git pull origin master` |
| 作業ブランチを作る | Team → Switch To → New Branch | `git switch -c issue-12-example` |
| 変更を記録する | Team → Commit（Git Staging） | `git add ...` → `git commit -m "..."` |
| GitHubへ送る | Team → Push Branch | `git push -u origin issue-12-example`（初回） |
| 最新状態を確認する | Git Staging / Git Repositories | `git status` |

※Eclipseのバージョンによってメニュー名や位置が異なる場合がある。また、Pullは**現在のブランチ**に対して実行されるため、`master`を更新するときは先に`master`に切り替える。

## 11. 公式参考資料

- [Eclipse EGit User Guide](https://help.eclipse.org/latest/topic/org.eclipse.egit.doc/help/EGit/User_Guide/User-Guide.html)
- [EGit Tasks（Clone・Branch・Commitなど）](https://help.eclipse.org/latest/topic/org.eclipse.egit.doc/help/EGit/User_Guide/Tasks.html)
- [GitHub：IssueとPull Requestの関連付け](https://docs.github.com/en/issues/tracking-your-work-with-issues/using-issues/linking-a-pull-request-to-an-issue)
- [GitHub：Projectsのクイックスタート](https://docs.github.com/en/issues/planning-and-tracking-with-projects/learning-about-projects/quickstart-for-projects)
- [GitHub：保護されたブランチ](https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/managing-protected-branches)

---

**迷ったときの基本原則：** 「Issueを確認する → 最新のmasterから作業ブランチを作る → 実装・テストする → Commit・Pushする → PRでレビューしてもらう → マージ後に最新のmasterを取得する」。
