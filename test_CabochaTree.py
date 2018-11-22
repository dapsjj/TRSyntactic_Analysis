from subprocess import Popen, PIPE
import shlex

def cabocha_command(txt, cmd=""):
    with Popen(shlex.split("cabocha  " + cmd), stdout=PIPE, stdin=PIPE) as p:
        output, err = p.communicate(input=txt.encode('utf-8'))
    return output.decode('utf-8')


if __name__ == '__main__':
    print(cabocha_command("太郎はこの本を渡した。とてもおもしろい本で良かった"))
