package team.gif.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import team.gif.lib.shootParams;
import team.gif.robot.Constants;
import team.gif.robot.Robot;

public class AmpPosition extends Command {

    public AmpPosition() {
        super();
        //addRequirements(); // Command is not actually controlling elevator or wrist, just setting new target position
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.nextShot = shootParams.AMP;
    }

    // Called every time the scheduler runs (~20ms) while the command is scheduled
    @Override
    public void execute() {
        Robot.elevator.setTargetPosition(Constants.Elevator.AMP_POS);

        Robot.wrist.setTargetPosition(Robot.nextShot.getWristAngle());
        Robot.shooter.setupAndRev(Robot.nextShot.getShooterRPM());
    }

    // Return true when the command should end, false if it should continue. Runs every ~20ms.
    @Override
    public boolean isFinished() {
        return true;
    }

    // Called when the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
}