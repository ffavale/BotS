import matplotlib.pyplot as plt
import os
import sys

LOGDIR = ""
DESTDIR = ""
SOURCES = []

def find_sources():
    for path in os.listdir(LOGDIR):
        if os.path.isfile(os.path.join(LOGDIR, path)):
            if path.endswith(".log"):
                SOURCES.append(path)

def parse_log(i_simfile: str):
    datapoints = []
    with open(i_simfile) as log:
        for line in log:
            if "Iteration count: " in line:
                line_lst = line.split()
                datapoint = (int(line_lst[4]), int(line_lst[8]), (float(line_lst[12]), float(line_lst[13]), float(line_lst[14]), float(line_lst[15])))
                datapoints.append(datapoint)

    return tuple(datapoints), i_simfile

def plot_sim(i_datapoints: tuple, i_simname: str):
    x = []
    y_pop = []
    y_f= []
    y_p= []
    y_c= []
    y_s= []
    for datapoint in i_datapoints:
        x.append(datapoint[0])
        y_pop.append(datapoint[1])
        y_f.append(datapoint[2][0] * 100)
        y_p.append(datapoint[2][1] * 100)
        y_c.append(datapoint[2][2] * 100)
        y_s.append(datapoint[2][3] * 100)
        #  print(f"Plotting datapoint {datapoint}")

    fig,ax = plt.subplots()

    plot_0 = ax.plot(x, y_f, color="blue", label="Ratio Faithful")
    plot_1 = ax.plot(x, y_p, color="green", label="Ratio Philanderer")
    plot_2 = ax.plot(x, y_c, color="red", label="Ratio Coy")
    plot_3 = ax.plot(x, y_s, color="orange", label="Ratio Fast")

    ax.set_xlabel("Iteration")
    ax.set_ylabel("Percentage of overall population")

    ax2=ax.twinx()
    plot_4 = ax2.plot(x, y_pop, color="black", label="Population")
    ax2.set_ylabel("Total population")

    lns = plot_0 + plot_1 + plot_2 + plot_3 + plot_4
    labels = [l.get_label() for l in lns]
    plt.legend(lns, labels, loc=0)
    plt.title(" ".join(i_simname.replace('-', ' ').split()[:2]))
    print(f"Plotting graph of {i_simname}")
    fig.savefig(f"{DESTDIR}/graph_{i_simname}.jpeg", format='jpeg', dpi=144, bbox_inches='tight')
    plt.close(fig)

def main():
    find_sources()
    fignum=1
    for simfile in SOURCES:
        fignum+=1
        data, name = parse_log(simfile)
        plot_sim(data, name)

if __name__ == "__main__":
    argc = len(sys.argv)

    #  Set the destination and source directories
    if argc == 1:
        LOGDIR = "."
        DESTDIR = "."
    elif argc == 2:
        LOGDIR = "."
        DESTDIR = sys.argv[1]
    else:
        LOGDIR = sys.argv[2]
        DESTDIR = sys.argv[1]

    print(f"Log directory set to {LOGDIR}")
    print(f"Destination directory set to {DESTDIR}")

    main()
