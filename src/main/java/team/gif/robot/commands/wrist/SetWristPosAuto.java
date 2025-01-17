package team.gif.robot.commands.wrist;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import team.gif.lib.shootParams;
import team.gif.robot.Robot;

public class SetWristPosAuto extends Command {

    public SetWristPosAuto() {
        super();
//        addRequirements(Robot.wrist);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
//        double time = Timer.getFPGATimestamp();
//        if (time - Robot.dcLeftPadTime < .300) {
//            System.out.println("double pass: " + (time - Robot.dcLeftPadTime));
//            Robot.wrist.setNextShotPass();
//            Robot.wrist.disableAutoAngle();
//        }
//        Robot.dcLeftPadTime = time;
//        Robot.nextShot = shootParams.AUTOSHOT;
    }

    // Called every time the scheduler runs (~20ms) while the command is scheduled
    @Override
    public void execute() {
//        if (Robot.nextShot == shootParams.PASS) {
//            new SetWristPos().schedule();
//            return;
//        }
        if (Robot.sensors.shooter()) {
            Robot.wrist.setWristAuto();
//            Robot.wrist.PIDWristMove();
        }
    }

    // Return true when the command should end, false if it should continue. Runs every ~20ms.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called when the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
}
