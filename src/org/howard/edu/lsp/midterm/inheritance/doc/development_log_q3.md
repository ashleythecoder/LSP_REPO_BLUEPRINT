# Development Log

## Resources used

### AI assistance: Claude (Anthropic), claude.ai
- **When:** October 26, 2026, after I completed my own implementation
- **How I used it:** I uploaded my finished submission and the exam document
  and asked Claude to check my work. I also asked it to help word this
  development log.
- **How it helped:** Claude compiled and ran the provided Drivers (Q1: 44/44
  passed; Q3: 12/12 passed) and confirmed my code ran correctly. It also
  flagged that my development log files were empty.
- **Changes made because of it:** None. I did not change any of my code or
  design documents based on Claude's review. I only added this log.

### Full conversation transcript
[can u check my work
Ran 4 commands, read a file
Reading through your submitted files now, then I’ll compile and run both Drivers.
Ran 5 commands
Your code is in good shape, but three of the four development logs and the whole Q4 reflection are empty, and that’s where you’d lose the most points.
I compiled and ran both Drivers myself.
Q1: 44 passed, 0 failed (25/25 functional).
Q3: 12 passed, 0 failed.
Must fix before submitting
File	Problem	Points at risk
crccards/doc/development_log.md	0 bytes	up to 3
design/doc/development_log_q2.md	0 bytes	up to 5
inheritance/doc/development_log_q3.md	0 bytes	up to 5
reflection/doc/ai_reflection.md	0 bytes	up to 10
Development logs: If you used no outside help, the file must contain exactly: “No external assistance was used.” If you used AI, notes, or websites (including this conversation), you have to disclose them. That means the full prompts and responses for AI, full URLs for websites, and a sentence on how each helped. An empty file counts as missing, and an undisclosed AI use would be an academic integrity problem on top of that.
Q4 reflection: This one has to be written entirely by you. The exam prohibits AI for generating, editing, or evaluating it, so I haven’t looked at it and won’t review it. You need all three parts: a specific benefit, a specific challenge, and how you’ll use AI going forward.
What looks good
Q1 (Book and Library)
The design is solid. The HashMap keyed by ISBN suits the ISBN lookups and duplicate check.
Library finds the book and delegates the state change to Book.
The three-book limit is checked before delegating, so a failed borrow changes nothing.
The Javadoc has class descriptions, authors, and @param/@return/@throws.
crccards.md is 4 sentences and covers both required points: why Library collaborates with Book but not the reverse, and why Library enforces the limit.
Q2 (design docs)
The evaluation cites Riel’s heuristics 2.1, 3.1, 3.2 and 3.5 accurately, and ties them to specific problems in the code (public fields, a god method, console output, swallowed exceptions).
The CRC cards use the required format.
The rationale is consistent with your Part 2 design. It adds a DatabaseRegistrationStore and a TextMessageConfirmationSender, and it justifies why TuitionCalculator owns the pricing rules.
Q3 (inheritance)
The hierarchy is clean: an abstract Employee with calculatePay() overridden in each subclass, and no conditionals.
The overtime math is correct, with 1.5× only on hours beyond 40.
The Javadoc is complete.
Minor suggestions (optional)
Book’s constructor doesn’t null-check its arguments, though Library.addBook does. That’s fine for the exam, but worth being consistent about.
The submission is a zip, but the exam says to push to GitHub. Make sure the repo matches this structure and has no .class or IDE files.
I can also help you draft the development log entries, as long as they reflect what you actually used.
make this better: "i used claude to check over my work"
Improving a development log entry for honesty and completeness.
Improving a development log entry for honesty and completeness.
Here’s a more complete version. The exam requires more than a one-line mention: the tool, what you asked, how it helped, and the full transcript.
markdown
# Development Log

## Resources used

### AI assistance: Claude (Anthropic), claude.ai
- **When:** October 26, 2026, after I finished my initial implementation
- **How I used it:** I wrote my own solution first, then uploaded my submission
  and the exam document and asked Claude to check my work. I used it only for
  review: running my code against the provided Driver and checking my
  documentation against the exam requirements.
- **How it helped:** Claude compiled and ran both provided Drivers (Q1: 44/44
  passed; Q3: 12/12 passed) and confirmed my code worked. It also pointed out
  that my development logs and reflection file were still empty.
- **Changes I made because of it:** [Be specific. If you changed nothing in
  your code, say so. If you changed something, name the file and what you
  changed.]

### Full conversation transcript
[Paste the complete conversation here: every prompt and every response.
The exam requires the full exchange, or a working shareable link to it.]
Before you use it:
Make sure it’s true. The draft assumes you wrote everything yourself and used Claude only to check it. If Claude or any other tool also helped you write or fix code or docs, say what it did. If you used notes, a textbook, or websites, add them too, with full URLs and how they helped.
Paste in the transcript. I can’t do this for you, and the log is incomplete without it. Use the share-link option for this chat if you prefer, but check that the link works while you’re logged out.
Put a version in each log that applies. For Q1, Q2 and Q3, include a version in development_log.md, development_log_q2.md and development_log_q3.md, tailored to what you used for that question. I only reviewed Q1 and Q3 code and your Q2 docs, so don’t claim I checked something I didn’t.
Don’t mention Claude in the Q4 reflection. I haven’t looked at that file and can’t, since Q4 has to be entirely your own work.
i didnt make any changes cause of you just used this chat for ai documentation
Clarifying what the mentioned chat usage actually meant.
Clarifying what the mentioned chat usage actually meant.
Then say that plainly. Here’s the updated version, with the “changes” line filled in:
markdown
# Development Log

## Resources used

### AI assistance: Claude (Anthropic), claude.ai
- **When:** October 26, 2026, after I completed my own implementation
- **How I used it:** I uploaded my finished submission and the exam document
  and asked Claude to check my work. I also asked it to help word this
  development log.
- **How it helped:** Claude compiled and ran the provided Drivers (Q1: 44/44
  passed; Q3: 12/12 passed) and confirmed my code ran correctly. It also
  flagged that my development log files were empty.
- **Changes made because of it:** None. I did not change any of my code or
  design documents based on Claude's review. I only added this log.

### Full conversation transcript
[Paste the complete conversation here: every prompt and every response,
including this one about wording the log.]
A few notes:
Include the whole chat in the transcript. That covers your first “can u check my work,” my review, and the follow-ups about this log. If you leave out the log-wording messages, the transcript is incomplete.
Use it in each log that applies. Only claim what really happened for that question. For Q2, I didn’t run anything. I only read your three design documents, so say “reviewed” there rather than “tested.”
If you used anything else, add it. That includes notes, a textbook, websites with full URLs, or another AI tool. If you did write all the code yourself with no help, this log is accurate as is.
Q4 stays separate. Nothing from this chat should go into your reflection, and you shouldn’t use me to edit it.
