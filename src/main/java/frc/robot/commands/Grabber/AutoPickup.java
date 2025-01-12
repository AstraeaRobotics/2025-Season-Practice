// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Grabber;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.subsystems.GrabberSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoPickup extends SequentialCommandGroup {
  /** Creates a new AutoPickup. */
  public AutoPickup(GrabberSubsystem grabber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelDeadlineGroup(new WaitCommand(1), new SetGrabberState(grabber, GrabberStates.kLoweredGrabber)),
      new ParallelDeadlineGroup(new WaitCommand(1), new SetGrabberState(grabber, GrabberStates.kIntakeCone)),
      new ParallelDeadlineGroup(new WaitCommand(1), new SetGrabberState(grabber, GrabberStates.kRaisedGrabber))
    );
  }
}
